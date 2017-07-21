package calculoIndicadores;

import domain.Empresa;
import domain.indicadores.Indicador;
import domain.repositorios.RepositorioIndicadores;

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
