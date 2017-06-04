package externos.calculoIndicadores;

import domain.Empresa;

public class Suma extends Operador{
	
	public Suma() {
		super();
		this.prioridad = 3;
	}
	
	public Double calcularValor(Empresa empresa, String periodo){
		return operandoUno.calcularValor(empresa,periodo) + operandoDos.calcularValor(empresa,periodo);
	}
}
