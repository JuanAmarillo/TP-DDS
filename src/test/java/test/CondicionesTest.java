package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import domain.Cuenta;
import domain.Empresa;
import domain.condiciones.condicionesPredeterminadas.CEmpresaMayorAntiguedad;
import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;
import domain.repositorios.RepositorioCondiciones;

public class CondicionesTest {
	
	private Empresa empresa;
	
	private void prepararEmpresa() {
		empresa = new Empresa();
		empresa.setNombre("Mocka-Cola");
		Set<Cuenta> cuentas = new HashSet<>();
		Cuenta cuentita = new Cuenta("ZZZ", "periodo", 12345.6);
		Cuenta cuentitaBis = new Cuenta("XXX", "periodo", 1000.0);
		cuentas.add(cuentita);
		cuentas.add(cuentitaBis);
		empresa.setCuentas(cuentas);
		empresa.setAnioFundacion(1900);
	}
	
	@Before
	public void init() {
		prepararEmpresa();
	}
	
	@Test
	public void testCumpleCondicionTaxativaDeAntiguedad() {
		TEmpresaMas10Años condicion = new TEmpresaMas10Años();
		condicion.setEmpresa(empresa);
		assertTrue(condicion.comparar());
	}
	
	@Test
	public void testCumpleCondicionComparativaDeAntiguedad() {
		Empresa empresaMasJoven = new Empresa();
		empresaMasJoven.setAnioFundacion(1950);
		CEmpresaMayorAntiguedad condicion = new CEmpresaMayorAntiguedad();
		condicion.setPrimerEmpresa(empresa).setSegundaEmpresa(empresaMasJoven);
		assertTrue(condicion.comparar());
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
}
