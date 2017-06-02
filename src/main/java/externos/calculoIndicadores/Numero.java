package externos.calculoIndicadores;

import domain.Empresa;

public class Numero implements Token {
	private Double valor;
	
	public Numero(double valor){
		this.valor = valor;
	}
	
	public double calcularValor(Empresa empresa, String periodo) {
		return valor;
	}
	
}
