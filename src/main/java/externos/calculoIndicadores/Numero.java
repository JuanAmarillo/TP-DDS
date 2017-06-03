package externos.calculoIndicadores;

import domain.Empresa;

public class Numero extends Token {
	private Double valor;
	
	public Numero(double valor){
		this.prioridad = 1;
		this.valor = valor;
	}
	
	public double calcularValor(Empresa empresa, String periodo) {
		return valor;
	}
	
}
