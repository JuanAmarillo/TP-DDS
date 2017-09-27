package domain.indicadores.calculoIndicadores;

import domain.Empresa;
import domain.indicadores.Indicador;
import domain.repositorios.RepositorioIndicadores;

public class IndicadorCalculo extends Calculable{

	private String nombre;
	
	public IndicadorCalculo(String nombre){
		this.prioridad = 0;
		this.nombre = nombre;
	}
	
	public Double calcularValor(Empresa empresa, String periodo) {
		Indicador indicador = RepositorioIndicadores.instance().findByName(nombre).get();
		return indicador.calcularIndicador(empresa, periodo);	
	}
	
	
}
