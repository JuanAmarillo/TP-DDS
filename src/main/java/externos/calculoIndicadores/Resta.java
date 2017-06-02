package externos.calculoIndicadores;

import domain.Empresa;

public class Resta extends Operador {

	public Resta(Token operandoUno, Token operandoDos) {
		super(operandoUno, operandoDos);
	}

	public double calcularValor(Empresa empresa, String periodo) {
		return operandoUno.calcularValor(empresa, periodo) - operandoDos.calcularValor(empresa, periodo);
	}

}
