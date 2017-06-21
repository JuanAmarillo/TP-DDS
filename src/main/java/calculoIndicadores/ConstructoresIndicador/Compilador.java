package calculoIndicadores.ConstructoresIndicador;

import java.util.List;
import java.util.Stack;

import calculoIndicadores.Calculable;
import calculoIndicadores.CuentaOIndicador;
import calculoIndicadores.Division;
import calculoIndicadores.Multiplicacion;
import calculoIndicadores.Numero;
import calculoIndicadores.Operador;
import calculoIndicadores.ParentesisIzquierdo;
import calculoIndicadores.Resta;
import calculoIndicadores.Suma;
import calculoIndicadores.Token;

public class Compilador extends TokenToOperationTranslator{
	private Stack<Calculable> lexemas;
	private Stack<Token> operadores;

	protected Compilador(List<String> tokens) {
		super(tokens);
		this.operadores = new Stack<Token>();
		this.lexemas =  new Stack<Calculable>();
	}
	
	public Calculable compilar(){
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
		else if(esUnOperador(token))
			operador(token);
		else if(esUnParentesisIzquierdo(token))
			parentesisIzquierdo();
		else if(esUnParentesisDerecho(token))
			parentesisDerecho();
		else if(esUnTexto(token))
			cuentaOIndicador(token);
	}
	
	private void numero(String token){
		Calculable numero = new Numero(Double.parseDouble(token));
		lexemas.push(numero);
	}
	
	private void operador(String token){
		Operador operador = null;
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
		ParentesisIzquierdo parentesisIzquierdo = new ParentesisIzquierdo();
		operadores.push(parentesisIzquierdo);
	}
	
	private void parentesisDerecho(){
		armarOperadoresDelParentesis();
	}
	
	private void cuentaOIndicador(String token){
		CuentaOIndicador cuenta = new CuentaOIndicador(token);
		lexemas.push(cuenta);
	}
	
	
	private void ingresarOperador(Operador operador){
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
