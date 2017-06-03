package externos.calculoIndicadores;

import domain.Empresa;

public class Resta extends Operador {

	public Resta() {
		super();
		this.prioridad = 2;
	}

	public Double calcularValor(Empresa empresa, String periodo) {
		return operandoUno.calcularValor(empresa, periodo) - operandoDos.calcularValor(empresa, periodo);
	}

}
