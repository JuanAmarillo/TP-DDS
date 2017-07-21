package unitTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import calculoIndicadores.ConstructoresIndicador.Analizador;

public class AnalizadorTest {

	Analizador analizador;

	public List<String> generarTokens(String ecuacion) {
		return new Analizador(ecuacion).generarTokens();
	}

	public List<String> lista(String... tokens) {
		return new ArrayList<>(Arrays.asList(tokens));
	}

	@Test
	public void generarTokensSumaTest() {
		assertEquals(lista("2", "+", "3"), generarTokens("2+ 3 "));
	}

	@Test
	public void generarTokensRestaTest() {
		assertEquals(lista("-", "2", "-", "2"), generarTokens("-2  - 2 "));
	}

	@Test
	public void generarTokensMultiplicacionTest() {
		assertEquals(lista("2", "*", "2", "*", "3"), generarTokens("2   * 2  *3 "));
	}

	@Test
	public void generarTokensDivisionTest() {
		assertEquals(lista("/", "/", "2", "/", "2", "/", "3"), generarTokens("/ / 2   / 2  /3 "));
	}
	
	@Test
	public void generarTokensIgualdadTest() {
		assertEquals(lista("/", "/", "2", "/", "2", "/", "3"), generarTokens("/ / 2   / 2  /3 "));
	}

	@Test
	public void generarTokensParentesisTest() {
		assertEquals(lista("(", "2", "+", "3", "*", "9", ")"), generarTokens("( 2 + 3 * 9 ) "));
	}

	
	@Test
	public void generarTokensNumerosConComaTest() {
		assertEquals(lista("2.0","+","3.0"), generarTokens("2.0 + 3.0"));
	}
	
	@Test
	public void generarTokensTextoSimpleTest() {
		assertEquals(lista("aprobame"), generarTokens("aprobame"));
	}
	
	@Test
	public void generarTokensTextoIgualacionTest() {
		assertEquals(lista("aprobame","=", "8"), generarTokens("aprobame = 8"));
	}

	public void generarTokensTextoConEspaciosYNumerosTest() {
		assertEquals(lista("Gaston todavia no me corrigio el tp de funcional del 2015"),
				generarTokens("Gaston todavia no me corrigio el tp de funcional del 2015"));
	}
	
	

}
