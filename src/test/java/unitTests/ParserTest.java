package unitTests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import calculoIndicadores.ConstructoresIndicador.Parser;
import exceptions.ParsingException;

public class ParserTest {
	
	public List<String> lista(String... tokens) {
		return new ArrayList<>(Arrays.asList(tokens));
	}
	
	public void parsear(String... tokens){
		new Parser(lista(tokens)).parsear();;
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
