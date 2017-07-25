package unitTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import domain.indicadores.calculoIndicadores.ConstructoresIndicador.Analizador;

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
	public void testGenerarTokensSuma() {
		elAnalisisDe("2+ 3  ").resulta("2", "+", "3");
	}

	@Test
	public void testGenerarTokensResta() {
		elAnalisisDe("2  - 2 ").resulta("2", "-", "2");
	}

	@Test
	public void testGenerarTokensMultiplicacion() {
		elAnalisisDe("2   * 2  *3 ").resulta("2", "*", "2", "*", "3");
	}

	@Test
	public void testGenerarTokensDivision() {
		elAnalisisDe("/ / 2   / 2  /3 ").resulta("/", "/", "2", "/", "2", "/", "3");
	}

	@Test
	public void testGenerarTokensIgualdad() {
		elAnalisisDe("/ / 2   / 2  /3 ").resulta("/", "/", "2", "/", "2", "/", "3");
	}

	@Test
	public void testGenerarTokensParentesis() {
		elAnalisisDe("( 2 + 3 * 9 ) ").resulta("(", "2", "+", "3", "*", "9", ")");
	}

	@Test
	public void testGenerarTokensNumerosConComa() {
		elAnalisisDe("2.0 + 3.0").resulta("2.0", "+", "3.0");
	}

	@Test
	public void testGenerarTokensTextoSimple() {
		elAnalisisDe("aprobame").resulta("aprobame");
	}

	@Test
	public void testGenerarTokensTextoConIgualacion() {
		elAnalisisDe("aprobame = 8").resulta("aprobame", "=", "8");
	}

	public void testGenerarTokensTextoConEspaciosYNumeros() {
		elAnalisisDe("Gaston todavia no me corrigio el tp de funcional del 2015")
				.resulta("Gaston todavia no me corrigio el tp de funcional del 2015");
	}

}
