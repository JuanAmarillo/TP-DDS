package externos;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

import domain.Cuenta;
import domain.Empresa;
import domain.indicadores.IndicadorCustom;
import domain.repositorios.RepositorioIndicadores;
import externos.calculoIndicadores.*;

public class AnalizadorMaximo {
	private Empresa empresa;
	private String periodo;
	private List<String> tokens;
	private Stack<Token> lexemas;
	private Stack<Token> operadores;
	
	
	public AnalizadorMaximo(){
		this.operadores = new Stack<Token>();
		this.lexemas =  new Stack<Token>();
	}
	
	public AnalizadorMaximo scan(IndicadorCustom indicador) {
		generarTokens(indicador.ecuacion);
		eliminarEspaciosInnecesarios();
		//tokens.forEach(lexema-> System.out.println(lexema)); 
		return this;
	}
	
	public Token compilar(){
		List<Token> tokens = asignarTokens(this.tokens);  
		armarArbolDeSintaxis(tokens);
		return lexemas.pop();
	}
	
	
	public void generarTokens(String ecuacion){
		String[] tokens = ecuacion.split("(?<=[-+()*/])|(?=[-+()*/])");
		this.tokens =  new LinkedList<String>(Arrays.asList(tokens));
	}
	
	public void eliminarEspaciosInnecesarios(){
		this.tokens = tokens.stream().map(unToken -> unToken.trim()).filter(unToken -> !unToken.isEmpty())
						.collect(Collectors.toList());
	}
	
	private List<Token> asignarTokens(List<String> tokens){
		return tokens.stream().map(unToken -> asignarToken(unToken)).collect(Collectors.toList());
	}
	
	private Token asignarToken(String token){
		if(esUnNumero(token))
			return new Numero(Double.parseDouble(token));
		if(esUnOperador(token))
			return operador(token);
		if(esUnParentesis(token))
			return parentesis(token);
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
	
	private Token parentesis(String token) {
		switch(token){
		case "(":
			return new ParentesisIzquierdo();
		case ")":
			return new ParentesisDerecho();
		}
		
		return null;
	}
	
	private void armarArbolDeSintaxis(List<Token> tokens){
		tokens.stream().forEach(unToken -> ingresarA(unToken));
		armarOperadoresRestantes();
	}
	
	private void ingresarA(Token token){
		if(esUnaConstante(token))
			lexemas.push(token);
		if(esUnOperador(token))
			ingresarOperador(token);
		if(esUnParentesisIzquierdo(token))
			operadores.push(token);
		if(esUnParentesisDerecho(token))
			armarOperadoresDelParentesis();
	
	}
	
	private boolean esUnaConstante(Token token) {
		return token.getPrioridad() == 0;
	}
	
	private boolean esUnOperador(Token token){
		return token.getPrioridad() > 2;
	}
	
	private boolean esUnParentesisDerecho(Token token) {
		return token.getPrioridad() == 1;
	}
	
	private boolean esUnParentesisIzquierdo(Token token) {
		return token.getPrioridad() == 2;
	}
	
	private void ingresarOperador(Token token){
		if(!operadores.empty()){
			if(operadores.peek().getPrioridad() >= token.getPrioridad())
				armarOperador();
		}
		operadores.push(token);
	}

	
	private void armarOperadoresDelParentesis(){
		while(!esUnParentesisIzquierdo(operadores.peek()))
			armarOperador();
		operadores.pop();
	}
	
	private void armarOperador(){
		Token operandoUno  = lexemas.pop();
		Token operandoDos  = lexemas.pop();
		Operador operador  = (Operador) operadores.pop();
		operador.asignarOperandos(operandoUno, operandoDos);
		lexemas.push(operador);
	}
	
	private void armarOperadoresRestantes(){
		while(!operadores.empty())
			armarOperador();
		
	}
	
}
