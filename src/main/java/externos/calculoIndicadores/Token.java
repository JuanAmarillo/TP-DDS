package externos.calculoIndicadores;

import domain.Empresa;

public abstract class Token {
	protected int prioridad;
	
	public int getPrioridad(){
		return this.prioridad;
	}
	
	abstract public double calcularValor(Empresa empresa, String periodo);
}
