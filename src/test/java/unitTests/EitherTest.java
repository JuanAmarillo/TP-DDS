package unitTests;

import org.junit.Test;

import domain.indicadores.EitherIndicador;
import static org.junit.Assert.assertEquals;

public class EitherTest {

	@Test
	public void seCalculoDosMasDosTest(){
		assertEquals(new EitherIndicador("calculado", 2.0 + 2.0).getValorString(),"4.0");
	}
	
	@Test
	public void huboErrorTest(){
		assertEquals(new EitherIndicador("calculado", new RuntimeException()).getValorString(),"No pudo calcularse");
	}
}
