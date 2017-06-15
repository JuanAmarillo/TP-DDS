package calculoIndicadores;

import domain.Empresa;

public class ParentesisDerecho extends Token {

	public ParentesisDerecho(){
		this.prioridad = 1;
	}
	
	public Double calcularValor(Empresa empresa, String periodo) {
		return null;
	}

}
