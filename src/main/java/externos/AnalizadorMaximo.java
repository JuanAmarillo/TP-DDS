package externos;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import domain.Cuenta;
import domain.Empresa;
import domain.indicadores.IndicadorCustom;
import domain.repositorios.RepositorioIndicadores;
import externos.calculoIndicadores.*;

public class AnalizadorMaximo {
	private Empresa empresa;
	private String periodo;
	private List<Token> lexemas;
	
	public AnalizadorMaximo(Empresa empresa,String periodo){
		this.empresa = empresa;
		this.periodo = periodo;
	}
	
	public AnalizadorMaximo scan(IndicadorCustom indicador) {
		List<String> tokens;
		tokens  = generarTokens(indicador.ecuacion);
		tokens  = eliminarEspaciosInnecesarios(tokens);
		lexemas = asignarToken(tokens);  
		return this;
	}
	
	public List<String> generarTokens(String ecuacion){
		String[] tokens = ecuacion.split("(?<=[-+()*/])|(?=[-+()*/])");
		return new LinkedList<String>(Arrays.asList(tokens));
	}
	
	public List<String> eliminarEspaciosInnecesarios(List<String> tokens){
		return tokens.stream().map(unToken -> unToken.trim()).filter(unToken -> !unToken.isEmpty())
				.collect(Collectors.toList());
	}
	
	public List<Token> asignarToken(List<String> tokens){
		return tokens.stream().map(unToken -> asignarToken(unToken)).collect(Collectors.toList());
	}
	
	public Token asignarToken(String token){
		if(esUnNumero(token))
			return new Numero(Double.parseDouble(token));
		if(esUnOperador(token))
			return operador(token);
		if(esUnaCuenta(token))
			return new CuentaCalculo(token);
		if(esUnIndicador(token))
			return new IndicadorCalculo(token);
		
		throw new RuntimeException("Invalid token");
	}
	
	private boolean esUnNumero(String token){
		return token.matches("[0-9]+([.][0-9]+)?");
	}
	
	private boolean esUnParentesis(String token){
		return token.matches("[()]");
	}
	
	private boolean esUnOperador(String token){
		return token.matches("[-+*/]");
	}
	
	
	private boolean esUnaCuenta(String cuenta){
		return empresa.contieneLaCuentaDePeriodo(cuenta, periodo);
	}
	
	private boolean esUnIndicador(String indicador){
		return RepositorioIndicadores.instance().contieneElIndicador(indicador);
	}
	
	private Token operador(String token){
		switch(token){
		case "+":
			return new Suma();
		case "-":
			return new Resta();
		case "*":
			return new Multiplicacion();
		case "/":
			return new Division();
		}
		
		return null;
	}
	
	
}
