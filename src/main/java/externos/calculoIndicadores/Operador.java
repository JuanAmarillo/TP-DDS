package externos.calculoIndicadores;

import domain.Empresa;

public abstract class Operador extends Token {
	Token operandoUno;
	Token operandoDos;
	
	public Operador(){
		this.prioridad = 2;
	}
	
	public void asignarOperandos(Token operandoUno,Token operandoDos){
		this.prioridad = 2;
		this.operandoUno = operandoUno;
		this.operandoDos = operandoDos;
	}
	
	abstract public double calcularValor(Empresa empresa, String periodo);
}
