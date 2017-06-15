package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import calculoIndicadores.ConstructoresIndicador.Analizador;
import domain.Cuenta;
import domain.Empresa;
import domain.indicadores.IndicadorCustom;
import domain.repositorios.RepositorioIndicadores;
import externos.AnalizadorDeIndicadores;
import externos.AnalizadorMaximo;
import externos.calculoIndicadores.*;;

public class EquationsTest {
	Empresa empresaMockeadaB;
	IndicadorCustom pasivoCorriente;
	IndicadorCustom pruebaAcida;
	AnalizadorDeIndicadores analizador;
	AnalizadorMaximo analizadorMax;

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
		analizadorMax = new AnalizadorMaximo(empresaMockeadaB,"2017");
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
	}

	@Test
	public void testDaPrioridadALosOperadores() {
		IndicadorCustom indicador = new IndicadorCustom();
		indicador.ecuacion = "5 - 50 * 2 + 3/3 - 1 + 2 *3 ";
	
		Token token = analizadorMax.scan(indicador).compilar();
		assertTrue(token.calcularValor(empresaMockeadaB, "2017").equals(-101.0));
	}
	
	@Test
	public void testDaPrioridadALosParentesis(){
		IndicadorCustom indicador = new IndicadorCustom();
		indicador.ecuacion = "(2*(3+4) + (5+2))/3";
	
		Token token = analizadorMax.scan(indicador).compilar();
		assertTrue(token.calcularValor(empresaMockeadaB, "2017").equals(7.0));
	}


	@Test
	public void testAceptaNumerosConComa() {
		IndicadorCustom indicador = new IndicadorCustom();
		indicador.ecuacion = "1.5 + 2.5 ";
		Token token = analizadorMax.scan(indicador).compilar();
		assertTrue(token.calcularValor(empresaMockeadaB, "2017").equals(4.0));
	}

	@Test
	public void testIndicadorConCuentas() {
		Token token = analizadorMax.scan(pasivoCorriente).compilar();
		assertTrue(token.calcularValor(empresaMockeadaB, "2017").equals(70.0));
	}

	@Test
	public void testIndicadorConIndicadores() {
		Token token = analizadorMax.scan(pruebaAcida).compilar();
		assertTrue(token.calcularValor(empresaMockeadaB, "2017").equals(3.0));
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
	public void testNuevoParserFunciona(){
		assertTrue(new Analizador(empresaMockeadaB, "2017").scan("hola = 2 + 5").parser());
		assertTrue(new Analizador(empresaMockeadaB, "2017").scan("hola = ((2+2) * chau)").parser());
		assertFalse(new Analizador(empresaMockeadaB, "2017").scan("hola = (2 + chau").parser());
		assertFalse(new Analizador(empresaMockeadaB, "2017").scan("hola = hola").parser());
	}
	
	@Test
	public void testSeCalculanLosIndicadoresDeFormaPolimorfica() {
		double sumaDeLosIndicadores = RepositorioIndicadores.instance().getIndicadoresCargados().stream().mapToDouble(ind -> ind.calcularIndicador(empresaMockeadaB, "2017")).sum();
		assertEquals(73.0 + 23.0 + 173.0,sumaDeLosIndicadores,0.0);
	}

}
