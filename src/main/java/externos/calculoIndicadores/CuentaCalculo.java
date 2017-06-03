package externos.calculoIndicadores;

import domain.Empresa;

public class CuentaCalculo extends Token{
	private String nombre;
	
	public CuentaCalculo(String nombre){
		this.prioridad = 1;
		this.nombre = nombre;
	}
	
	public Double calcularValor(Empresa empresa, String periodo) {
		return empresa.getValorDeLaCuenta(nombre, periodo);
		
	}
	
}
