package externos.calculoIndicadores;

import domain.Empresa;

public abstract class Operador extends Token {
	Token operandoUno;
	Token operandoDos;
	
	
	public void asignarOperandos(Token operandoDos,Token operandoUno){
		this.operandoUno = operandoUno;
		this.operandoDos = operandoDos;
	}
	
	abstract public Double calcularValor(Empresa empresa, String periodo);
}
