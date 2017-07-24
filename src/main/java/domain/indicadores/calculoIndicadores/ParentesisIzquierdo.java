package domain.indicadores.calculoIndicadores;

public class ParentesisIzquierdo implements Token {
	protected int prioridad;
	
	public ParentesisIzquierdo(){
		this.prioridad = 2;
	}

	public int getPrioridad() {
		return this.prioridad;
	}

}
