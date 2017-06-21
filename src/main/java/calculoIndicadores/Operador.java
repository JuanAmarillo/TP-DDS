package calculoIndicadores;

import domain.Empresa;

public abstract class Operador extends Calculable {
	Calculable operandoUno;
	Calculable operandoDos;
	
	
	public void asignarOperandos(Calculable operandoDos,Calculable operandoUno){
		this.operandoUno = operandoUno;
		this.operandoDos = operandoDos;
	}
	
	abstract public Double calcularValor(Empresa empresa, String periodo);
}
