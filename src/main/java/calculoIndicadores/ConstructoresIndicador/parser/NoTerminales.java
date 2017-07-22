package calculoIndicadores.ConstructoresIndicador.parser;

import java.util.ArrayList;
import java.util.List;

import calculoIndicadores.Token;

public class NoTerminales {
	private static List<Token> noTerminales = new ArrayList<Token>();
	
	public void agregarTokens(Token token){
		noTerminales.add(token);
	}
	
	public boolean esUnNoTerminal(String token){
		return true;
	}
}
