package unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Empresa;
import domain.condiciones.Condicion;
import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionTaxativa;
import domain.condiciones.OperadoresCondicion.Menor;
import domain.condiciones.condicionesPredeterminadas.CEmpresaMayorAntiguedad;
import domain.condiciones.condicionesPredeterminadas.CEndeudamiento;
import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;
import domain.indicadores.indicadoresPredeterminados.Antiguedad;
import domain.repositorios.RepositorioCondiciones;
import mocks.IndicadorNoCalculableMock;
import mocks.PreparadorDeEmpresas;

public class CondicionesTest {
	List<Empresa> empresas;

	private List<Empresa> aplicarCondicionALista(Condicion condicion) {
		List<Empresa> listaEmpresas = empresas;
		listaEmpresas = condicion.aplicarCondicion(listaEmpresas);
		return listaEmpresas;
	}

	@Before
	public void init() {
		empresas = PreparadorDeEmpresas.prepararEmpresas();
	}

	private Empresa prepararEmpresa(int i) { // 0=Coca-Cola __ 1=Sorny __
												// 2=MagnetBox __ 3=Pepsi-co __
												// 4=Panaphonics
		return empresas.get(i);
	}

	@Test
	public void testCumpleCondicionTaxativaDeAntiguedad() {
		TEmpresaMas10Años condicion = new TEmpresaMas10Años();
		assertTrue(condicion.evaluarCondicionEnPeriodo(prepararEmpresa(0), "pepito"));
	}

	@Test
	public void testCumpleCondicionComparativaDeAntiguedad() {
		Empresa empresaMasJoven = new Empresa("joven");
		empresaMasJoven.setAnioFundacion(1950);
		CEmpresaMayorAntiguedad condicion = new CEmpresaMayorAntiguedad();
		condicion.setIndicador(new Antiguedad());
		assertTrue(0 != condicion.evaluarCondicionEnPeriodo(prepararEmpresa(0), prepararEmpresa(1), "pepito"));
	}

	@Test
	public void testSeAgreganAlRepoLasPredeterminadas() {
		Integer condicionesPredeterminadas = 4;
		assertEquals(condicionesPredeterminadas, RepositorioCondiciones.instance().cantidadDeCondiciones());
	}

	@Test
	public void testCondicionPredeterminadaValor() {
		CEmpresaMayorAntiguedad condicion = new CEmpresaMayorAntiguedad();
		assertFalse(condicion.esCustom());
	}

	@Test
	public void testCondicionOrdenaListaPorEmpresaMasJoven() {
		CondicionComparativa condicion = new CondicionComparativa("Prueba Sort", null, null);
		condicion.setIndicador(new Antiguedad());
		condicion.setOperador(new Menor());
		List<Empresa> listaEmpresas = aplicarCondicionALista(condicion);
		assertTrue(listaEmpresas.get(0).esLaMismaEmpresaQue(prepararEmpresa(4)));
	}

	@Test(expected = RuntimeException.class)
	public void testNoSePuedeCalcularCondicion() {
		CondicionTaxativa condicion = new CondicionTaxativa("Calculame esta", null, null, null);
		condicion.setIndicador(new IndicadorNoCalculableMock());
		aplicarCondicionALista(condicion);
	}

	@Test
	public void testSeFiltraSegunCondicionTaxativa() {
		CondicionTaxativa condicion = new TEmpresaMas10Años();
		List<Empresa> resultado = aplicarCondicionALista(condicion);
		assertEquals(2, resultado.size());
		assertTrue(resultado.get(0).esLaMismaEmpresaQue(prepararEmpresa(0)));
	}

	@Test
	public void testEndeudamiento() {
		CondicionComparativa condicion = new CEndeudamiento();
		List<Empresa> resultado = aplicarCondicionALista(condicion);
		assertTrue(resultado.get(0).esLaMismaEmpresaQue(prepararEmpresa(1)));
	}
}