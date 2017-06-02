package externos.calculoIndicadores;

import domain.Empresa;

public class CuentaCalculo implements Token{
	private String nombre;
	
	public CuentaCalculo(String nombre){
		this.nombre = nombre;
	}
	
	public double calcularValor(Empresa empresa, String periodo) {
		return empresa.getValorDeLaCuenta(nombre, periodo);
		
	}
	
}
