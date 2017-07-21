package unitTests;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
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
	public void indicadorSinTerminalLuegoDeLaIgualdadTest(){
		parsear("hola", "=");
	}
	
	@Test(expected = ParsingException.class)
	public void indicadorSinTerminalLuegoDeUnOperadorTest(){
		parsear("hola", "=", "2", "+");
	}
	
	@Test(expected = ParsingException.class)
	public void indicadorNoPuedeTenerOperacionLuegoDeUnaIgualdadTest(){
		parsear("hola", "=", "*");
	}
	
	@Test(expected = ParsingException.class)
	public void luegoDeUnNoTerminalNoPuedeIrOtroNoTerminaTestl(){
		parsear("hola", "=", "2", "*", "+", "3");
	}
	
	@Test(expected = ParsingException.class)
	public void indicadorConParentesisSinCerrarTest(){
		parsear("hola", "=", "(", "2", "+", "3");
	}
	
	@Test(expected = ParsingException.class)
	public void indicadorConParentesisDerechoDeMasTest(){
		parsear("hola", "=", "(", "2", "+", "3",")",")");
	}
	

}
