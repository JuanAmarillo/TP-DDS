package externos;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

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
	
	
	public AnalizadorMaximo(Empresa empresa, String periodo){
		this.empresa = empresa;
		this.periodo = periodo;
		this.operadores = new Stack<Token>();
		this.lexemas =  new Stack<Token>();
	}
	
	public AnalizadorMaximo scan(IndicadorCustom indicador) {
		generarTokens(indicador.ecuacion);
		eliminarEspaciosInnecesarios();
		//tokens.forEach(lexema-> System.out.println(lexema)); 
		return this;
	}
	
	public void generarTokens(String ecuacion){
		String[] tokens = ecuacion.split("(?<=[-+()*/])|(?=[-+()*/])");
		this.tokens =  new LinkedList<String>(Arrays.asList(tokens));
	}
	
	public void eliminarEspaciosInnecesarios(){
		this.tokens = tokens.stream().map(unToken -> unToken.trim()).filter(unToken -> !unToken.isEmpty())
				.collect(Collectors.toList());
	}
	
	public Token compilar(){
		armarArbolDeSintaxis();
		return lexemas.pop();
	}
	
	
	private void armarArbolDeSintaxis(){
		tokens.stream().forEach(unToken -> asignarToken(unToken));
		armarOperadoresRestantes();
	}
	
	private void asignarToken(String token){
		if(esUnNumero(token))
			numero(token);
		if(esUnOperador(token))
			operador(token);
		if(esUnParentesisIzquierdo(token))
			parentesisIzquierdo();
		if(esUnParentesisDerecho(token))
			parentesisDerecho();
		if(esUnaCuenta(token))
			cuenta(token);
		if(esUnIndicador(token))
			indicador(token);
	}
	

	private boolean esUnNumero(String token){
		return token.matches("[0-9]+([.][0-9]+)?");
	}
	
	private boolean esUnParentesisIzquierdo(String token){
		return token.matches("[(]");
	}
	
	private boolean esUnParentesisDerecho(String token){
		return token.matches("[)]");
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
	
	private void numero(String token){
		Token numero = new Numero(Double.parseDouble(token));
		lexemas.push(numero);
	}
	
	private void operador(String token){
		Token operador = null;
		switch(token){
		case "+":
			operador =  new Suma();
			break;
		case "-":
			operador =  new Resta();
			break;
		case "*":
			operador =  new Multiplicacion();
			break;
		case "/":
			operador =  new Division();
			break;
		}
		
		ingresarOperador(operador);
	}
	
	private void parentesisIzquierdo(){
		Token parentesisIzquierdo = new ParentesisIzquierdo();
		operadores.push(parentesisIzquierdo);
	}
	
	private void parentesisDerecho(){
		armarOperadoresDelParentesis();
	}
	
	private void cuenta(String token){
		Token cuenta = new CuentaCalculo(token);
		lexemas.push(cuenta);
	}
	
	private void indicador(String token){
		Token indicador = new IndicadorCalculo(token);
		lexemas.push(indicador);
	}
	
	
	private void ingresarOperador(Token token){
		if(!operadores.empty() && anteriorTieneMayorPrioridad(token))
				armarOperador();
		
		operadores.push(token);
	}

	private boolean anteriorTieneMayorPrioridad(Token token) {
		return operadores.peek().getPrioridad() >= token.getPrioridad();
	}

	
	private void armarOperadoresDelParentesis(){
		while(!esUnParentesisIzquierdo(operadores.peek()))
			armarOperador();
		operadores.pop();
	}
	
	private boolean esUnParentesisIzquierdo(Token token) {
		return token.getPrioridad() == 2;
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
