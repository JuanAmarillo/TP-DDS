package unitTests;

import org.junit.Test;

import domain.indicadores.calculoIndicadores.ConstructoresIndicador.Parser;
import exceptions.ParsingException;
import ui.vm.VmUtils;

public class ParserTest {
	
	public void parsear(String... tokens){
		new Parser(VmUtils.convertirALista(tokens)).parsear();;
	}
	
	@Test
	public void parserAceptaTexto(){
		parsear("soy un indicador", "=", "Soy una cuenta");
	}	
	
	@Test
	public void parserAceptaSuma(){
		parsear("suma", "=", "4","+","4");
	}	
	
	@Test
	public void parserAceptaResta(){
		parsear("suma", "=", "4","-","4");
	}
	
	@Test
	public void parserAceptaMultiplicacion(){
		parsear("suma", "=", "4","*","4");
	}
	
	@Test
	public void parserAceptaDivision(){
		parsear("suma", "=", "4","/","4");
	}
	
	@Test
	public void parserAceptaParentesis(){
		parsear("suma", "=", "(","(","2","+","3",")","*","4",")");
	}
	
	@Test(expected = ParsingException.class)
	public void indicadorConNombreInvalidoFallaTest(){
		parsear("n@mbre", "=", "3");
	}
	
	@Test(expected = ParsingException.class)
	public void indicadorSinAsignacionFallaTest(){
		parsear("nombre");
	}
	
	@Test(expected = ParsingException.class)
	public void indicadorConOperacionesAntesDeLaIgualdadFallaTest(){
		parsear("spider", "+", "man", "=", "spiderman");
	}
	
	@Test(expected = ParsingException.class)
	public void indicadorSinTerminalLuegoDeLaIgualdadFallaTest(){
		parsear("hola", "=");
	}
	
	@Test(expected = ParsingException.class)
	public void indicadorSinTerminalLuegoDeUnOperadorFallaTest(){
		parsear("hola", "=", "2", "+");
	}
	
	@Test(expected = ParsingException.class)
	public void indicadorNoPuedeTenerOperacionLuegoDeUnaIgualdadTest(){
		parsear("hola", "=", "*");
	}
	
	@Test(expected = ParsingException.class)
	public void luegoDeUnNoTerminalNoPuedeIrOtroNoTerminaTest(){
		parsear("hola", "=", "2", "*", "+", "3");
	}
	
	@Test(expected = ParsingException.class)
	public void indicadorConParentesisSinCerrarFallaTest(){
		parsear("hola", "=", "(", "2", "+", "3");
	}
	
	@Test(expected = ParsingException.class)
	public void indicadorConParentesisDerechoDeMasFallaTest(){
		parsear("hola", "=", "(", "2", "+", "3",")",")");
	}
	
	@Test(expected = ParsingException.class)
	public void indicadorNoSePuedeLLamarASiMismo(){
		parsear("hola", "=", "hola");
	}
	

}
