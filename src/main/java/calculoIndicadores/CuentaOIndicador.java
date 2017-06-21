package calculoIndicadores;

import domain.Empresa;
import domain.repositorios.RepositorioIndicadores;
import interfaces.Indicador;

public class CuentaOIndicador extends Calculable{
	private String nombre;
	
	public CuentaOIndicador(String nombre){
		this.prioridad = 0;
		this.nombre = nombre;
	}
	
	public Double calcularValor(Empresa empresa, String periodo) {
		if(esUnaCuenta(empresa,periodo))
			return valorCuenta(empresa, periodo);
		else
			return valorIndicador(empresa, periodo);		
	}
	
	private boolean esUnaCuenta(Empresa empresa, String periodo){
		return empresa.contieneLaCuentaDePeriodo(nombre, periodo);
	}

	private Double valorIndicador(Empresa empresa, String periodo) {
		Indicador indicador = RepositorioIndicadores.instance().buscarIndicador(nombre);
		return indicador.calcularIndicador(empresa, periodo);
	}

	private Double valorCuenta(Empresa empresa, String periodo) {
		return empresa.getValorDeLaCuenta(nombre, periodo);
	}
	
}
