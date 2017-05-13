package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import domain.Cuenta;
import domain.Empresa;
import net.objecthunter.exp4j.ExpressionBuilder;
import externos.ParseadorDeIndicadores;

public class EquationsTest {

	ParseadorDeIndicadores par = new ParseadorDeIndicadores();
	String ecuacionDePrueba;
	String ecuacionSencilla;
	List<String> paraProbar;
	Empresa empresaMockeadaB;
	
	private void prepararEmpresa() {
		empresaMockeadaB =  new Empresa();
		Cuenta cuentita = new Cuenta();
		Set<Cuenta> cuentas = new HashSet<>();
		cuentita.setNombre("ROA");
		cuentita.setPeriodo("periodo");
		cuentita.setBalance(new Double(500));
		empresaMockeadaB.setNombre("Mocka-Cola");
		cuentas.add(cuentita);
		empresaMockeadaB.setCuentas(cuentas);
	}
	
	@Before
	public void init() {
		paraProbar = new ArrayList<String>();
		paraProbar.add("ROE");
		paraProbar.add("ROI");
		paraProbar.add("ROA");
		ecuacionDePrueba = "Ind = 'ROE' + 50 - 'ROI' * 'ROA'";
		ecuacionSencilla = "Ind = 'ROA' -20";
		prepararEmpresa();
	}
	
	@Test
    public void testExpressionBuilder2() throws Exception {
        double result = new ExpressionBuilder("cos(x)")
                .variables("x")
                .build()
                .setVariable("x", Math.PI)
                .evaluate();
        assertEquals(-1d, result, 0d);
	}
	
	@Test
	public void testParseNombreDeCuentas() {
		Set<String> roe = par.obtenerNombresDeCuentas(ecuacionDePrueba);
		assertTrue(paraProbar.equals(roe));
	}
	
	@Test
	public void testObtenerNombreIndicador() {
		String nombre = par.getNombreIndicador(ecuacionDePrueba);
		assertTrue("Ind".equals(nombre));
	}
	
	@Test
	public void testSubString() {
		String asd = "abcdefghijklmnñopqrstuvwxyz";
		int ad = asd.indexOf("a");
		System.out.println(ad);
	}
	
}
