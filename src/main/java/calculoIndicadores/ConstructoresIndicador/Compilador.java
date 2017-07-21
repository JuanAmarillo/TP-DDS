package calculoIndicadores.ConstructoresIndicador;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import calculoIndicadores.*;
public class Compilador {
	
	private Stack<Calculable> lexemas;
	private Stack<Token> operadores;

	public Compilador() {
		this.operadores = new Stack<Token>();
		this.lexemas =  new Stack<Calculable>();
	}
	
	public Calculable compilar(List<String> tokens){
		armarArbolDeSintaxis(tokens);
		return lexemas.pop();
	}
	
	
	private void armarArbolDeSintaxis(List<String> tokens){
		tokens.stream().forEach(unToken -> asignarToken(unToken));
		armarOperadoresRestantes();
	}
	
	private void asignarToken(String token){
		obtenerOperacion(token).createOperation(token, this);
	}

	private TokenToOperation obtenerOperacion(String token) {
		return valoresDeTokens().stream().filter(operacion-> operacion.matches(token))
				.findFirst().get();
	}

	public List<TokenToOperation> valoresDeTokens() {
		return Arrays.asList(TokenToOperation.values());
	}
	
	public void numero(String token){
		Calculable numero = new Numero(Double.parseDouble(token));
		lexemas.push(numero);
	}
	
	public void parentesisIzquierdo(){
		ParentesisIzquierdo parentesisIzquierdo = new ParentesisIzquierdo();
		operadores.push(parentesisIzquierdo);
	}
	
	public void parentesisDerecho(){
		armarOperadoresDelParentesis();
	}
	
	public void cuenta(String token){
		CuentaCalculo cuenta = new CuentaCalculo(token);
		lexemas.push(cuenta);
	}
	
	public void indicador(String token){
		IndicadorCalculo indicador = new IndicadorCalculo(token);
		lexemas.push(indicador);
	}
	
	
	public void ingresarOperador(Operador operador){
		if(!operadores.empty() && anteriorTieneMayorPrioridad(operador))
				armarOperador();
		
		operadores.push(operador);
	}

	private boolean anteriorTieneMayorPrioridad(Operador operador) {
		return operadores.peek().getPrioridad() >= operador.getPrioridad();
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
		Calculable operandoUno  =  lexemas.pop();
		Calculable operandoDos  =  lexemas.pop();
		Operador operador  = (Operador) operadores.pop();
		operador.asignarOperandos(operandoUno, operandoDos);
		lexemas.push(operador);
	}
	
	private void armarOperadoresRestantes(){
		while(!operadores.empty())
			armarOperador();
		
	}
	

}
