package unitTests.indicadores;

import org.junit.Test;

import domain.indicadores.IndicadorCalculado;
import domain.indicadores.IndicadorCustom;

import static org.junit.Assert.assertEquals;

public class IndicadorCalculadoTest {

	@Test
	public void testSeCalculoDosMasDos() {
		assertEquals(new IndicadorCalculado(new IndicadorCustom(),null, null,  2.0 + 2.0).getValorString(), "4.0");
	}

	@Test
	public void testHuboError() {
		assertEquals(new IndicadorCalculado(new IndicadorCustom(), null, null).getValorString(), "No pudo calcularse");
	}
}
