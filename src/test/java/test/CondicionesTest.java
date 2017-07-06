package test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import domain.Cuenta;
import domain.Empresa;
import domain.condiciones.Condicion;
import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionTaxativa;
import domain.condiciones.condicionesPredeterminadas.CEmpresaMayorAntiguedad;
import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;
import domain.indicadores.Indicador;
import domain.indicadores.indicadoresPredeterminados.Antiguedad;
import domain.indicadores.indicadoresPredeterminados.Solvencia;
import domain.repositorios.RepositorioCondiciones;
import domain.repositorios.RepositorioEmpresas;
import exceptions.NoSePuedeBorrarUnPredeterminadoException;
import exceptions.NoSePuedeCalcularException;
import mocks.IndicadorNoCalculableMock;

public class CondicionesTest {
	
	List<Empresa> empresas;
	
	private Empresa empresa1 = new Empresa();
	private Empresa empresa2 = new Empresa();
	
	
	
	private List<Empresa> aplicarCondicionALista(Condicion condicion) {
		List<Empresa> listaEmpresas = new ArrayList<Empresa>();
		listaEmpresas.addAll(Arrays.asList(empresa1,empresa2));
		listaEmpresas = condicion.aplicarCondicion(listaEmpresas, "pascuas");
		return listaEmpresas;
	}
	
	@Before
	public void init() {
		empresas = PreparadorDeEmpresas.prepararEmpresas();
		empresa1 = prepararEmpresa(0);
		empresa2 = prepararEmpresa(1);
	}
	
	private Empresa prepararEmpresa(int i) {
		return empresas.get(i);
	}

	@Test
	public void testCumpleCondicionTaxativaDeAntiguedad() {
		TEmpresaMas10Años condicion = new TEmpresaMas10Años();
		assertTrue(condicion.aplicarComparacion(empresa1,"pepito"));
	}
	
	@Test
	public void testCumpleCondicionComparativaDeAntiguedad() {
		Empresa empresaMasJoven = new Empresa();
		empresaMasJoven.setAnioFundacion(1950);
		CEmpresaMayorAntiguedad condicion = new CEmpresaMayorAntiguedad();
		condicion.setIndicador(new Antiguedad());
		assertTrue( 0 != condicion.filterMethod(empresa1,empresa2,"pepito"));
	}
	
	@Test
	public void testSeAgreganAlRepoLasPredeterminadas() {
		int cantidadCondiciones = RepositorioCondiciones.instance().cantidadDeCondiciones();
		assertEquals(2,cantidadCondiciones);
	}
	
	@Test
	public void testCondicionPredeterminadaValor() {
		CEmpresaMayorAntiguedad condicion = new CEmpresaMayorAntiguedad();
		assertFalse(condicion.esCustom);
	}
	
	@Test(expected = NoSePuedeBorrarUnPredeterminadoException.class)
	public void testNoSePuedeBorrarCondicionPredeterminada() {
		RepositorioCondiciones.instance().eliminarCondicion("Taxativa - Empresa de mas de 10 años");
	}
	
	@Test
	public void testSePuedeEliminarUnaCondicion() {
		CondicionComparativa condicion = new CondicionComparativa("pepito");
		RepositorioCondiciones.instance().agregarCondicion(condicion);
		assertEquals(3,RepositorioCondiciones.instance().cantidadDeCondiciones());
		RepositorioCondiciones.instance().eliminarCondicion("Comparativa - pepito");
		assertEquals(2,RepositorioCondiciones.instance().cantidadDeCondiciones());
	}
	
	@Test
	public void testCondicionOrdenaLista() {
		CondicionComparativa condicion = new CondicionComparativa("Prueba Sort");
		condicion.setIndicador(new Antiguedad());
		condicion.setOperador("<");
		List<Empresa> listaEmpresas = aplicarCondicionALista(condicion);
		assertTrue(listaEmpresas.get(0).esLaMismaEmpresaQue(empresa1));
	}

	@Test(expected = NoSePuedeCalcularException.class)
	public void testNoSePuedeCalcularCondicion() {
		CondicionTaxativa condicion = new CondicionTaxativa("Calculame esta");
		condicion.setIndicador(new IndicadorNoCalculableMock());
		aplicarCondicionALista(condicion);
	}
	
	@Test
	public void testSeFiltraSegunCondicionTaxativa() {
		CondicionTaxativa condicion = new TEmpresaMas10Años();
		List<Empresa> resultado = aplicarCondicionALista(condicion);
		assertEquals(1,resultado.size());
		assertTrue(resultado.get(0).esLaMismaEmpresaQue(empresa1));
	}
}
