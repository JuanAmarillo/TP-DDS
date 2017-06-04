package externos.calculoIndicadores;

import domain.Empresa;

public class Multiplicacion extends Operador {

	public Multiplicacion() {
		super();
		this.prioridad = 4;
	}

	public Double calcularValor(Empresa empresa, String periodo) {
		return operandoUno.calcularValor(empresa, periodo) * operandoDos.calcularValor(empresa, periodo);
	}

}

