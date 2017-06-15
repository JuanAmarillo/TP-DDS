package calculoIndicadores;

import domain.Empresa;

public class ParentesisIzquierdo extends Token {

	public ParentesisIzquierdo(){
		this.prioridad = 2;
	}
	
	public Double calcularValor(Empresa empresa, String periodo) {
		return null;
	}

}
