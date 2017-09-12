package unitTests.analizadores;

import org.junit.Test;

import domain.indicadores.calculoIndicadores.ConstructoresIndicador.Parser;
import exceptions.ParsingException;
import ui.vm.VmUtils;

public class ParserTest {
	
	public void parsear(String... tokens){
		new Parser(VmUtils.convertirALista(tokens)).parsear();;
	}
	
	@Test
	public void testParserAceptaTexto(){
		parsear("soy un indicador", "=", "Soy una cuenta");
	}	
	
	@Test
	public void testParserAceptaSuma(){
		parsear("suma", "=", "4","+","4");
	}	
	
	@Test
	public void testParserAceptaResta(){
		parsear("suma", "=", "4","-","4");
	}
	
	@Test
	public void testParserAceptaMultiplicacion(){
		parsear("suma", "=", "4","*","4");
	}
	
	@Test
	public void testParserAceptaDivision(){
		parsear("suma", "=", "4","/","4");
	}
	
	@Test
	public void testParserAceptaParentesis(){
		parsear("suma", "=", "(","(","2","+","3",")","*","4",")");
	}
	
	@Test(expected = ParsingException.class)
	public void testIndicadorConNombreInvalidoFalla(){
		parsear("n@mbre", "=", "3");
	}
	
	@Test(expected = ParsingException.class)
	public void testIndicadorSinAsignacionFallas(){
		parsear("nombre");
	}
	
	@Test(expected = ParsingException.class)
	public void testIndicadorConOperacionesAntesDeLaIgualdadFalla(){
		parsear("spider", "+", "man", "=", "spiderman");
	}
	
	@Test(expected = ParsingException.class)
	public void testIndicadorSinTerminalLuegoDeLaIgualdadFalla(){
		parsear("hola", "=");
	}
	
	@Test(expected = ParsingException.class)
	public void testIndicadorSinTerminalLuegoDeUnOperadorFalla(){
		parsear("hola", "=", "2", "+");
	}
	
	@Test(expected = ParsingException.class)
	public void testIndicadorNoPuedeTenerOperacionLuegoDeUnaIgualdad(){
		parsear("hola", "=", "*");
	}
	
	@Test(expected = ParsingException.class)
	public void testLuegoDeUnNoTerminalNoPuedeIrOtroNoTermina(){
		parsear("hola", "=", "2", "*", "+", "3");
	}
	
	@Test(expected = ParsingException.class)
	public void testIndicadorConParentesisSinCerrarFalla(){
		parsear("hola", "=", "(", "2", "+", "3");
	}
	
	@Test(expected = ParsingException.class)
	public void testIndicadorConParentesisDerechoDeMasFalla(){
		parsear("hola", "=", "(", "2", "+", "3",")",")");
	}
	
	@Test(expected = ParsingException.class)
	public void testIndicadorNoSePuedeLLamarASiMismo(){
		parsear("hola", "=", "hola");
	}
	

}
