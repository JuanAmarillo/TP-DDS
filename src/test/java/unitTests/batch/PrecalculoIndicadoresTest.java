package unitTests.batch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.quartz.JobExecutionException;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import batchProccessing.precalculoIndicadores.CalculoDeIndicadoresProgramado;
import batchProccessing.precalculoIndicadores.ContenedorValoresARecalcular;
import domain.Cuenta;
import domain.Empresa;
import domain.indicadores.Indicador;
import domain.indicadores.IndicadorCalculado;

import domain.indicadores.indicadoresPredeterminados.ROA;
import domain.indicadores.indicadoresPredeterminados.ROE;
import domain.login.Usuario;
import domain.repositorios.RepositorioEmpresas;
import domain.repositorios.RepositorioIndicadores;
import domain.repositorios.RepositorioIndicadoresCalculados;
import domain.repositorios.RepositorioUsuarios;

public class PrecalculoIndicadoresTest extends AbstractPersistenceTest{

	private Empresa empresa;
	private Indicador roe;
	private Indicador roa;
	private Usuario usuario;
	
	@Before
	public void antes() {
		agregarEmpresa();
		setearIndicadores();
		agregarCalculados();
		ContenedorValoresARecalcular.cargarNuevaInstancia();
	}

	private void agregarCalculados() {
		agregarIndicadorCalculado(roe);
		agregarIndicadorCalculado(roa);
	}

	private void setearIndicadores() {
		usuario = new Usuario();
		roe = new ROE();
		roa = new ROA();
		usuario.agregarIndicador(roe);
		usuario.agregarIndicador(roa);
		RepositorioUsuarios.instance().agregar(usuario);
		RepositorioEmpresas.instance().agregar(empresa);
	}

	private void agregarIndicadorCalculado(Indicador ind) {
		RepositorioIndicadoresCalculados.instance().agregarValores(ind.calcular(empresa, "Siempre"));
	}

	private IndicadorCalculado obtenerDelIndicador(Indicador indicador) {
		return RepositorioIndicadoresCalculados.instance().find("indicador_id", indicador.getId().toString()).get();
	}
	
	private void agregarEmpresa() {
		empresa = new Empresa("Manaos");
		empresa.setAnioFundacion(1995);
		Cuenta cuenta = new Cuenta("Beneficio", "Siempre", 10000.0);
		Cuenta cuenta2 = new Cuenta("PatrimonioNeto", "Siempre", 1000.0);
		empresa.setCuentas(Stream.of(cuenta, cuenta2).collect(Collectors.toSet()));
	}
	
	private void cambiarValorCuenta(String nombreCuenta, Double valorNuevo) {
		empresa.buscarCuentaDe(nombreCuenta, "Siempre").get().setBalance(valorNuevo);
	}
	
	@Test
	public void actualizarUnaCuentaAgregaAlContenedor() {
		Cuenta cuenta = empresa.buscarCuentaDe("Beneficio", "Siempre").get();
		empresa.actualizarValores(cuenta, new Cuenta("Beneficio", "Siempre", 1000.0));
		assertEquals(1, ContenedorValoresARecalcular.instance().cantidadDeValoresARecalcular());
	}
	
	@Test
	public void elValorCalculadoEsElCorrecto() {
		assertEquals(10.0, obtenerDelIndicador(roe).getValorCalculado().get(), 0.0);
	}

	@Test
	public void actualizaElValorDelIndicadorCalculado() {
		CalculoDeIndicadoresProgramado prog = new CalculoDeIndicadoresProgramado();
		cambiarValorCuenta("Beneficio", 5000.0);
		prog.agregarAlRepositorio(empresa, "Siempre", roe);
		assertEquals(5.0, obtenerDelIndicador(roe).getValorCalculado().get(), 0.0);
	}

	@Test
	public void noSePudoCalcularROA() {
		assertTrue(obtenerDelIndicador(roa).getValorString().equals("No pudo calcularse"));
	}

	@Test
	public void agregoCuentasYRoaSeCalcula() throws JobExecutionException {
		empresa.agregarCuentas(Arrays.asList(new Cuenta("BeneficioEconomico", "Siempre", 10.0), new Cuenta("ActivoTotal", "Siempre", 5.0)).stream().collect(Collectors.toSet()));
		CalculoDeIndicadoresProgramado prog = new CalculoDeIndicadoresProgramado();
		ContenedorValoresARecalcular.instance().agregarEmpresaPeriodo(empresa, "Siempre");
		prog.calcularIndicador(roa, ContenedorValoresARecalcular.instance().getList());
		assertTrue(obtenerDelIndicador(roa).getValorString().equals("2.0"));
	}
	
	@Test
	public void execute() throws JobExecutionException {
		ContenedorValoresARecalcular.instance().agregarEmpresaPeriodo(empresa, "Siempre");
		CalculoDeIndicadoresProgramado proc = new CalculoDeIndicadoresProgramado();
		Cuenta cuenta = new Cuenta("BeneficioEconomico", "Siempre", 5000.0);
		Cuenta cuenta2 = new Cuenta("ActivoTotal", "Siempre", 1000.0);
		Cuenta cuenta3 = new Cuenta("Beneficio", "Siempre", 50.0);
		empresa.agregarCuentas(Arrays.asList(cuenta,cuenta2, cuenta3).stream().collect(Collectors.toSet()));
		proc.execute(null);
		RepositorioIndicadoresCalculados.instance().getElementos().stream().forEach(it -> print(it));
		assertTrue(obtenerDelIndicador(roa).getValorString().equals("5.0"));
		assertEquals(50.0/1000.0, obtenerDelIndicador(roe).getValorCalculado().get(), 0.0);
	}
	
	@Override
	public EntityManager entityManager() {
		return RepositorioIndicadoresCalculados.instance().getEntityManager();
	}
	
	public void print(IndicadorCalculado ind) {
		System.out.println("Nombre " + ind.getIndicador().getNombre()+ " empresa " +  ind.getEmpresa().getNombre()+ "periodo "+ ind.getPeriodo() + " valor " + ind.getValorString());
	}
}
