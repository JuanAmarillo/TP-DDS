package domain.indicadores.calculoIndicadores;

import domain.Empresa;

public class CuentaCalculo extends Calculable{

	private String nombre;
	
	public CuentaCalculo(String nombre){
		this.prioridad = 0;
		this.nombre = nombre;
	}
	
	public Double calcularValor(Empresa empresa, String periodo) {
		return empresa.getValorDeLaCuenta(nombre, periodo);		
	}
	
}
