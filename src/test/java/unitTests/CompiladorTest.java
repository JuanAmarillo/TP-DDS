package unitTests;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import calculoIndicadores.Calculable;
import calculoIndicadores.IndicadorCalculo;
import calculoIndicadores.ConstructoresIndicador.Compilador;
import domain.indicadores.Indicador;
import domain.repositorios.RepositorioIndicadores;

public class CompiladorTest {
	
	public Calculable arbolDeSintaxis;
	
	private LinkedList<String> convertirALista(String[] tokens) {
		return new LinkedList<String>(Arrays.asList(tokens));
	}
	
	public CompiladorTest compilar(String ...tokens){
		arbolDeSintaxis = new Compilador().compilar(convertirALista(tokens));
		return this;
	}
	
	public void obtiene(Double resultado){
		assertEquals(arbolDeSintaxis.calcularValor(null, null), resultado);
	}
	
	public void mockearRepositorioIndicador(Indicador indicadorMockeado){
		RepositorioIndicadores instance = mock(RepositorioIndicadores.class);
		when(instance.contieneElIndicador("indicador")).thenReturn(true);
		when(instance.contieneElIndicador("cuenta")).thenReturn(false);
		when(instance.buscarIndicador("indicador")).thenReturn(Optional.of(indicadorMockeado));
		RepositorioIndicadores.setInstance(instance);
		
	}
	
	public Indicador mockearIndicador(){
		Indicador  indicadorMockeado = mock(Indicador.class);
		when(indicadorMockeado.calcularIndicador(null, null)).thenReturn(4.0);
		return indicadorMockeado;
	}
	
	public void mockearRepositorioEmpresa(){
		
	}
	
	@Before
	public void init(){
		Indicador indicadorMockeado = mockearIndicador();
		mockearRepositorioIndicador(indicadorMockeado);
		mockearRepositorioEmpresa();
	}
	
	@Test
	public void compilaSumaTest(){
		compilar("2","+","2","+","5").obtiene(9.0);
	}
	
	@Test
	public void compilaRestaTest(){
		compilar("3","-","7","-","1").obtiene(-5.0);
	}
	
	@Test
	public void compilaMultiplicacionTest(){
		compilar("3","*","7","*","2").obtiene(42.0);
	}
	
	@Test
	public void respetaPrioridadDeMultiplicacionTest(){
		compilar("2","+","3","*","5").obtiene(17.0);
	}
	
	@Test
	public void compilaDivisionTest(){
		compilar("6","/","3","/","2").obtiene(1.0);
	}
	
	@Test
	public void respetaPrioridadDeDivisionTest(){
		compilar("2","-","10","/","5").obtiene(0.0);
	}
	
	@Test
	public void respetaPrioridadDeLosParetensisTest(){
		compilar("(","2","+","3",")","*","4").obtiene(20.0);
	}
	
	@Test(expected = RuntimeException.class)
	public void compilarPotenciacionFallaTest(){
		compilar("2","^","3");
	}
	
	@Test(expected = RuntimeException.class)
	public void compilarUnaExpresionQueEmpiezaConOperadorFalla(){
		compilar("*","5");
	}
	
	@Test(expected = RuntimeException.class)
	public void compilarUnaExpresionQueTerminaConOperadorFalla(){
		compilar("6","*");
	}
	
	@Test(expected = RuntimeException.class)
	public void compilarUnaExpresionQueLeFaltaParentesisFalla(){
		compilar("(","2","+","3");
	}
	
	
	@Test
	public void compilaIndicadoresTest(){
		compilar("indicador","+","5").obtiene(9.0);
	}
	
	@Test
	public void operacionCombinadaTest(){
		compilar("20","/","(","3","*","4","-","2",")","+","indicador").obtiene(6.0);
	}
	
}
