package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Cuenta;
import domain.Empresa;
import domain.indicadores.IndicadorCustom;
import domain.repositorios.RepositorioIndicadores;
import externos.AnalizadorDeIndicadores;;

public class EquationsTest {
	Empresa empresaMockeadaB;
	IndicadorCustom pasivoCorriente;
	IndicadorCustom pruebaAcida;
	AnalizadorDeIndicadores analizador;

	private void prepararEmpresa() {
		empresaMockeadaB = new Empresa();
		empresaMockeadaB.setNombre("Mocka-Cola");

		Set<Cuenta> cuentas = new HashSet<Cuenta>();
		Cuenta cajaBancos = new Cuenta("Caja y bancos", "2017", 150.0);
		Cuenta inversiones = new Cuenta("Inversiones", "2017", 60.0);
		Cuenta deudasBancarias = new Cuenta("Deudas Bancarias", "2017", 50.0);
		Cuenta deudasComerciales = new Cuenta("Deudas Comerciales", "2017", 15.0);
		Cuenta deudasDelEstado = new Cuenta("Deudas del Estado", "2017", 5.0);
		cuentas.add(cajaBancos);
		cuentas.add(inversiones);
		cuentas.add(deudasBancarias);
		cuentas.add(deudasComerciales);
		cuentas.add(deudasDelEstado);
		empresaMockeadaB.setCuentas(cuentas);
	}

	private void cargarIndicadores() {
		analizador = new AnalizadorDeIndicadores(empresaMockeadaB,"2017");
		
		pasivoCorriente = new IndicadorCustom();
		pasivoCorriente.nombre = "Pasivo Corriente";
		pasivoCorriente.ecuacion = "Deudas Bancarias + Deudas Comerciales + Deudas del Estado";
		RepositorioIndicadores.instance().agregarIndicador(pasivoCorriente);

		pruebaAcida = new IndicadorCustom();
		pruebaAcida.nombre = "Prueba Acida";
		pruebaAcida.ecuacion = "(Caja y bancos + Inversiones) / Pasivo Corriente";
		RepositorioIndicadores.instance().agregarIndicador(pruebaAcida);
	}

	@Before
	public void init() {
		prepararEmpresa();
		cargarIndicadores();
	}
	
	@After
	public void finalize() {
		RepositorioIndicadores.resetSingleton();
		RepositorioIndicadores.agregarPredeterminados();
	}

	@Test
	public void testIndicadorSinVariables() {
		IndicadorCustom indicador = new IndicadorCustom();
		indicador.ecuacion = "((2-5)*(5-3))/((2*3) - 8) ";
		assertTrue(analizador.scan(indicador).parser().equals(3.0));
	}

	@Test
	public void testNoDaPrioridadALaMultiplicacion() {
		IndicadorCustom indicador = new IndicadorCustom();
		indicador.ecuacion = "2 * 50 - 40 ";
		assertFalse(analizador.scan(indicador).parser().equals(60));
	}

	@Test
	public void testAceptaNumerosConComa() {
		IndicadorCustom indicador = new IndicadorCustom();
		indicador.ecuacion = "1.5 + 2.5 ";
		assertTrue(analizador.scan(indicador).parser().equals(4.0));
	}

	@Test
	public void testIndicadorConCuentas() {
		assertTrue(analizador.scan(pasivoCorriente).parser().equals(70.0));
	}

	@Test
	public void testIndicadorConIndicadores() {
		assertTrue(analizador.scan(pruebaAcida).parser().equals(3.0));
	}

	@Test(expected = RuntimeException.class)
	public void testIndicadorConOperadorIncorrectoFalla() {
		IndicadorCustom indicador = new IndicadorCustom();
		indicador.ecuacion = "2!3";
		analizador.scan(indicador).parser();
	}

	@Test(expected = RuntimeException.class)
	public void testIndicadorConCuentaOIndicadorFaltanteFalla() {
		IndicadorCustom indicador = new IndicadorCustom();
		indicador.ecuacion = "No existo :p";
		analizador.scan(indicador).parser();
	}
	
	@Test
	public void testSeCalculanLosIndicadoresDeFormaPolimorfica() {
		double sumaDeLosIndicadores = RepositorioIndicadores.instance().getIndicadoresCargados().stream().mapToDouble(ind -> ind.calcularIndicador(empresaMockeadaB, "2017")).sum();
		assertEquals(73.0 + 23.0 + 173.0,sumaDeLosIndicadores,0.0);
	}

}
