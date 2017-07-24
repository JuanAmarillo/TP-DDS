package unitTests;

import org.junit.Test;

import domain.indicadores.IndicadorCalculado;
import static org.junit.Assert.assertEquals;

public class IndicadorCalculadoTest {

	@Test
	public void seCalculoDosMasDosTest() {
		assertEquals(new IndicadorCalculado("indicador", 2.0 + 2.0).getValorString(), "4.0");
	}

	@Test
	public void huboErrorTest() {
		assertEquals(new IndicadorCalculado("indicador").getValorString(), "No pudo calcularse");
	}
}
