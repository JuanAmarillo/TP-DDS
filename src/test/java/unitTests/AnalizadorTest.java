package unitTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import calculoIndicadores.ConstructoresIndicador.Analizador;

public class AnalizadorTest {

	private List<String> tokens;

	public AnalizadorTest elAnalisisDe(String ecuacion) {
		tokens = new Analizador(ecuacion).generarTokens();
		return this;
	}

	public List<String> lista(String... tokens) {
		return new ArrayList<>(Arrays.asList(tokens));
	}

	public void resulta(String... tokens) {
		assertEquals(lista(tokens), this.tokens);
	}

	@Test
	public void generarTokensSumaTest() {
		elAnalisisDe("2+ 3  ").resulta("2", "+", "3");
	}

	@Test
	public void generarTokensRestaTest() {
		elAnalisisDe("-2  - 2 ").resulta("-", "2", "-", "2");
	}

	@Test
	public void generarTokensMultiplicacionTest() {
		elAnalisisDe("2   * 2  *3 ").resulta("2", "*", "2", "*", "3");
	}

	@Test
	public void generarTokensDivisionTest() {
		elAnalisisDe("/ / 2   / 2  /3 ").resulta("/", "/", "2", "/", "2", "/", "3");
	}

	@Test
	public void generarTokensIgualdadTest() {
		elAnalisisDe("/ / 2   / 2  /3 ").resulta("/", "/", "2", "/", "2", "/", "3");
	}

	@Test
	public void generarTokensParentesisTest() {
		elAnalisisDe("( 2 + 3 * 9 ) ").resulta("(", "2", "+", "3", "*", "9", ")");
	}

	@Test
	public void generarTokensNumerosConComaTest() {
		elAnalisisDe("2.0 + 3.0").resulta("2.0", "+", "3.0");
	}

	@Test
	public void generarTokensTextoSimpleTest() {
		elAnalisisDe("aprobame").resulta("aprobame");
	}

	@Test
	public void generarTokensTextoConIgualacionTest() {
		elAnalisisDe("aprobame = 8").resulta("aprobame", "=", "8");
	}

	public void generarTokensTextoConEspaciosYNumerosTest() {
		elAnalisisDe("Gaston todavia no me corrigio el tp de funcional del 2015")
				.resulta("Gaston todavia no me corrigio el tp de funcional del 2015");
	}

}
