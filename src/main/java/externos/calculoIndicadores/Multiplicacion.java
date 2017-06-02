package externos.calculoIndicadores;

import domain.Empresa;

public class Multiplicacion extends Operador {

	public Multiplicacion(Token operandoUno, Token operandoDos) {
		super(operandoUno, operandoDos);
	}

	public double calcularValor(Empresa empresa, String periodo) {
		return operandoUno.calcularValor(empresa, periodo) * operandoDos.calcularValor(empresa, periodo);
	}

}

