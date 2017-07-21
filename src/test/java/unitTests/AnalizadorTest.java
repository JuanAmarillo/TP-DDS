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
	public void generarTokensSuma() {
		assertEquals(lista("2", "+", "2"), generarTokens("2+ 2 "));
	}

	@Test
	public void generarTokensResta() {
		assertEquals(lista("-", "2", "-", "2"), generarTokens("-2  - 2 "));
	}

	@Test
	public void generarTokensMultiplicacion() {
		assertEquals(lista("2", "*", "2", "*", "3"), generarTokens("2   * 2  *3 "));
	}

	@Test
	public void generarTokensDivision() {
		assertEquals(lista("/", "/", "2", "/", "2", "/", "3"), generarTokens("/ / 2   / 2  /3 "));
	}
	
	@Test
	public void generarTokensIgualdad() {
		assertEquals(lista("/", "/", "2", "/", "2", "/", "3"), generarTokens("/ / 2   / 2  /3 "));
	}

	@Test
	public void generarTokensParentesis() {
		assertEquals(lista("(", "2", "+", "3", "*", "9", ")"), generarTokens("( 2 + 3 * 9 ) "));
	}

	
	@Test
	public void generarTokensNumerosConComa() {
		assertEquals(lista("2.0","+","3.0"), generarTokens("2.0 + 3.0"));
	}
	
	@Test
	public void generarTokensTextoSimple() {
		assertEquals(lista("aprobame"), generarTokens("aprobame"));
	}
	
	@Test
	public void generarTokensTextoIgualacion() {
		assertEquals(lista("aprobame","=", "8"), generarTokens("aprobame = 8"));
	}

	public void generarTokensTextoConEspaciosYNumeros() {
		assertEquals(lista("Gaston todavia no me corrigio el tp de funcional del 2015"),
				generarTokens("Gaston todavia no me corrigio el tp de funcional del 2015"));
	}
	
	

}
