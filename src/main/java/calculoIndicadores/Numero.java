package calculoIndicadores;

import domain.Empresa;

public class Numero extends Calculable {
	private Double valor;
	
	public Numero(double valor){
		this.prioridad = 0;
		this.valor = valor;
	}
	
	public Double calcularValor(Empresa empresa, String periodo) {
		return valor;
	}
	
}
