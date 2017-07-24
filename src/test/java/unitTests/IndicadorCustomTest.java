package unitTests;

import domain.indicadores.calculoIndicadores.Calculable;
import domain.indicadores.calculoIndicadores.Numero;
import domain.indicadores.IndicadorCustom;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class IndicadorCustomTest {
	public Calculable calculoExitosoMockeado;
	public Calculable calculoFallidoMockeado;
	
	public void mockearCalculoExitoso(){
		calculoExitosoMockeado = mock(Numero.class);
		when(calculoExitosoMockeado.calcularValor(null, null)).thenReturn(1.0);
	}
	
	public void mockearCalculoFallido(){
		calculoFallidoMockeado = mock(Numero.class);
		when(calculoFallidoMockeado.calcularValor(null, null)).thenThrow(new RuntimeException());
	}
	
	public Double calcular(Calculable calculo) {
		return new IndicadorCustom("indicador","1",calculo).calcularIndicador(null, null);
	}
	
	public void elCalculoDa(Calculable calculable, Double resultado) {
		assertEquals(calcular(calculable), resultado);
	}
	
	@Before
	public void init(){
		mockearCalculoExitoso();
		mockearCalculoFallido();
	}
	
	@Test
	public void elCalculoSeEfectuaConExitoTest(){
		elCalculoDa(calculoExitosoMockeado, 1.0);
	}
	
	@Test(expected = RuntimeException.class)
	public void elCalculoFallaTest(){
		elCalculoDa(calculoFallidoMockeado, 0.0);
	}



}
