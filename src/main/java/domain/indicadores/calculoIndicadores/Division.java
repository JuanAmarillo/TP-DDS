package domain.indicadores.calculoIndicadores;

import domain.Empresa;

public class Division extends Operador{

	public Division() {
		super();
		this.prioridad = 4;
	}


	public Double calcularValor(Empresa empresa, String periodo) {
		return operandoUno.calcularValor(empresa, periodo) / operandoDos.calcularValor(empresa, periodo) ;
	}
	
	

}
