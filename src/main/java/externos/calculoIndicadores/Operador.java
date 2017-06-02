package externos.calculoIndicadores;

import domain.Empresa;

public abstract class Operador {
	Token operandoUno;
	Token operandoDos;
	
	public Operador(Token operandoUno,Token operandoDos){
		this.operandoUno = operandoUno;
		this.operandoDos = operandoDos;
	}
	public double calcularValor(Empresa empresa, String periodo){
		return operandoUno.calcularValor(empresa,periodo) + operandoDos.calcularValor(empresa,periodo);
	}
}
