package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import calculoIndicadores.Token;
import calculoIndicadores.ConstructoresIndicador.Analizador;
import domain.Cuenta;
import domain.Empresa;
import domain.indicadores.IndicadorCustom;
import domain.repositorios.RepositorioIndicadores;


public class EquationsTest {
	Empresa empresa;
	String periodo;
	IndicadorCustom pasivoCorriente;
	IndicadorCustom pruebaAcida;
	Analizador analizador;

	private void prepararEmpresa() {
		empresa = new Empresa();
		empresa.setNombre("Mocka-Cola");
		periodo = "2017";

		Set<Cuenta> cuentas = new HashSet<Cuenta>();
		Cuenta cajaBancos = new Cuenta("Caja y bancos", periodo, 150.0);
		Cuenta inversiones = new Cuenta("Inversiones", periodo, 60.0);
		Cuenta deudasBancarias = new Cuenta("Deudas Bancarias", periodo, 50.0);
		Cuenta deudasComerciales = new Cuenta("Deudas Comerciales", periodo, 15.0);
		Cuenta deudasDelEstado = new Cuenta("Deudas del Estado", periodo, 5.0);
		cuentas.add(cajaBancos);
		cuentas.add(inversiones);
		cuentas.add(deudasBancarias);
		cuentas.add(deudasComerciales);
		cuentas.add(deudasDelEstado);
		empresa.setCuentas(cuentas);
	}

	private void cargarIndicadores() {
		analizador = new Analizador();
		
		pasivoCorriente = new IndicadorCustom("Pasivo Corriente = Deudas Bancarias + Deudas Comerciales + Deudas del Estado");
		RepositorioIndicadores.instance().agregarIndicador(pasivoCorriente);

		pruebaAcida = new IndicadorCustom("Prueba Acida = (Caja y bancos + Inversiones) / Pasivo Corriente ");
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
		String indicador = "5 - 50 * 2 + 3/3 - 1 + 2 *3 ";
	
		Token token = analizador.scan(indicador).compilar();
		assertTrue(token.calcularValor(empresa, periodo).equals(-101.0));
	}
	
	@Test
	public void testDaPrioridadALosParentesis(){
		String indicador = "(2*(3+4) + (5+2))/3";
	
		Token token = analizador.scan(indicador).compilar();
		assertTrue(token.calcularValor(empresa, periodo).equals(7.0));
	}


	@Test
	public void testAceptaNumerosConComa() {
		String indicador = "1.5 + 2.5 ";
		Token token = analizador.scan(indicador).compilar();
		assertTrue(token.calcularValor(empresa, periodo).equals(4.0));
	}

	@Test
	public void testIndicadorConCuentas() {
		Token token = analizador.scan(pasivoCorriente.ecuacion).compilar();
		assertTrue(analizador.scan(pasivoCorriente.ecuacion).sePuedeCalcular(empresa, periodo));
		assertTrue(token.calcularValor(empresa, periodo).equals(70.0));
	}

	@Test
	public void testIndicadorConIndicadores() {
		Token token = analizador.scan(pruebaAcida.ecuacion).compilar();
		assertTrue(analizador.scan(pruebaAcida.ecuacion).sePuedeCalcular(empresa, periodo));
		assertTrue(token.calcularValor(empresa, periodo).equals(3.0));
	}
	
	
	@Test
	public void testIndicadorConCuentaOIndicadorNoExistenteNoPuedeSerCalculado(){
		String indicador = "2*pepe";
		assertEquals(analizador.scan(indicador).sePuedeCalcular(empresa, periodo),false);
	}
	
	@Test
	public void testSeCalculanLosIndicadoresDeFormaPolimorfica() {
		double sumaDeLosIndicadores = RepositorioIndicadores.instance().obtenerCustoms().stream().mapToDouble(ind -> ind.calcularIndicador(empresa, periodo)).sum();
		assertEquals(73.0,sumaDeLosIndicadores,0.0);
	}

}
