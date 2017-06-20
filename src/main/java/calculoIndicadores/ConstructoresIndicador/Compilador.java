package calculoIndicadores.ConstructoresIndicador;

import java.util.List;
import java.util.Stack;

import calculoIndicadores.CuentaOIndicador;
import calculoIndicadores.Division;
import calculoIndicadores.Multiplicacion;
import calculoIndicadores.Numero;
import calculoIndicadores.Operador;
import calculoIndicadores.ParentesisIzquierdo;
import calculoIndicadores.Resta;
import calculoIndicadores.Suma;
import calculoIndicadores.Token;
import domain.Empresa;

public class Compilador extends OperacionesDisponibles{
	private Stack<Token> lexemas;
	private Stack<Token> operadores;

	protected Compilador(List<String> tokens) {
		super(tokens);
		this.operadores = new Stack<Token>();
		this.lexemas =  new Stack<Token>();
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
	
	private void cuentaOIndicador(String token){
		Token cuenta = new CuentaOIndicador(token);
		lexemas.push(cuenta);
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
