package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import calculoIndicadores.Calculable;
import calculoIndicadores.ConstructoresIndicador.Analizador;
import domain.Cuenta;
import domain.Empresa;
import domain.indicadores.BuilderIndicadorCustom;
import domain.indicadores.IndicadorCustom;
import domain.repositorios.RepositorioIndicadores;


public class CalculoIndicadoresTest {
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
		BuilderIndicadorCustom builderPasivo = new BuilderIndicadorCustom("Pasivo Corriente = Deudas Bancarias + Deudas Comerciales + Deudas del Estado");
		pasivoCorriente = builderPasivo.analizar().generarIndicador().build();
		RepositorioIndicadores.instance().agregarIndicador(pasivoCorriente);
		BuilderIndicadorCustom builderAcido = new BuilderIndicadorCustom("Prueba Acida = (Caja y bancos + Inversiones) / Pasivo Corriente ");
		pruebaAcida = builderAcido.analizar().generarIndicador().build();
		RepositorioIndicadores.instance().agregarIndicador(pruebaAcida);
		
	}

	private Calculable compilarExpresion(String expresion ) {
		return analizador.scan(expresion).compilar();
	}
	
	private void puedeCalcular(String expresion,boolean resultado) {
		assertEquals(analizador.scan(expresion).sePuedeCalcular(empresa, periodo),resultado);
	}
	
	private void elCalculoDa(Calculable calculo,double resultado) {
		assertTrue(calculo.calcularValor(empresa, periodo).equals(resultado));
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
		Calculable calculo = compilarExpresion("5 - 50 * 2 + 3/3 - 1 + 2 *3 ");
		elCalculoDa(calculo,-101.0);
	}

	@Test
	public void testDaPrioridadALosParentesis(){
		Calculable calculo = compilarExpresion("(2*(3+4) + (5+2))/3");
		elCalculoDa(calculo, 7.0);
	}

	@Test
	public void testAceptaNumerosConComa() {
		Calculable calculo = compilarExpresion("1.5 + 2.5 ");
		elCalculoDa(calculo, 4.0);
	}

	@Test
	public void testIndicadorConCuentas() {
		Calculable calculo = compilarExpresion(pasivoCorriente.ecuacion);
		puedeCalcular(pasivoCorriente.ecuacion,true);
		elCalculoDa(calculo, 70.0);
	}

	@Test
	public void testIndicadorConIndicadores() {
		Calculable calculo = compilarExpresion(pruebaAcida.ecuacion);
		puedeCalcular(pruebaAcida.ecuacion,true);
		elCalculoDa(calculo, 3.0);
	}
		
	@Test
	public void testIndicadorConCuentaOIndicadorNoExistenteNoPuedeSerCalculado(){
		String expresion = "2*pepe";
		puedeCalcular(expresion, false);
	}
	
	@Test
	public void testSeCalculanLosIndicadoresDeFormaPolimorfica() {
		double sumaDeLosIndicadores = RepositorioIndicadores.instance().obtenerCustoms().stream().mapToDouble(ind -> ind.calcularIndicador(empresa, periodo)).sum();
		assertEquals(73.0,sumaDeLosIndicadores,0.0);
	}
}
