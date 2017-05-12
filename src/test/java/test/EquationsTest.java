package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import net.objecthunter.exp4j.ExpressionBuilder;
import externos.ParseadorDeIndicadores;

public class EquationsTest {

	ParseadorDeIndicadores par = new ParseadorDeIndicadores();
	String ecuacionDePrueba;
	List<String> paraProbar;
	
	@Before
	public void init() {
		paraProbar = new ArrayList<String>();
		paraProbar.add("ROE");
		paraProbar.add("ROI");
		paraProbar.add("ROA");
		ecuacionDePrueba = "Ind = 'ROE' + 50 - 'ROI' * 'ROA'";
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
		List<String> roe = par.obtenerNombresDeCuentas(ecuacionDePrueba);
		assertTrue(paraProbar.equals(roe));
	}
	
	@Test
	public void obtenerNombreEmpresa() {
		String nombre = par.obtenerNombreIndicador(ecuacionDePrueba);
		assertTrue("Ind".equals(nombre));
	}
	
	@Test
	public void testSubString() {
		String asd = "abcdefghijklmnñopqrstuvwxyz";
		int ad = asd.indexOf("a");
		System.out.println(ad);
	}
	
	
}
