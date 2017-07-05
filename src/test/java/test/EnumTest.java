package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import calculoIndicadores.ConstructoresIndicador.EnumLoco;

public class EnumTest {
	
	public void encontro(String token, EnumLoco lel){
		assertEquals(Arrays.asList(EnumLoco.values()).stream().filter(a->a.matches(token)).findFirst()
				.get(),lel);
	}
	@Test
	public void as(){
		encontro("+", EnumLoco.SUMA);
		encontro("-", EnumLoco.RESTA);
		encontro("*", EnumLoco.MULTIPLICACION);
		encontro("/", EnumLoco.DIVISION);
		encontro("ho la", EnumLoco.CUENTAOINDICADOR);
		encontro("(", EnumLoco.PARENTESISIZQUIERDO);
		encontro("12345896570598", EnumLoco.NUMERO);
		
	}
	
	
}
