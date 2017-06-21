package calculoIndicadores;

public class ParentesisDerecho implements Token {
	protected int prioridad;
	
	public ParentesisDerecho(){
		this.prioridad = 1;
	}
	
	public int getPrioridad() {
		return this.prioridad;
	}

}
