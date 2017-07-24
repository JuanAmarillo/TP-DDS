package domain.indicadores.calculoIndicadores;

import domain.Empresa;

public abstract class Calculable implements Token {
	protected int prioridad;
	
	public int getPrioridad(){
		return this.prioridad;
	}
	
	abstract public Double calcularValor(Empresa empresa, String periodo);
}
