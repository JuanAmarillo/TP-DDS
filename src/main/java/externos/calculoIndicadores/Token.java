package externos.calculoIndicadores;

import domain.Empresa;

public abstract class Token {
	protected int prioridad;
	
	public int getPrioridad(){
		return this.prioridad;
	}
	
	abstract public Double calcularValor(Empresa empresa, String periodo);

}
