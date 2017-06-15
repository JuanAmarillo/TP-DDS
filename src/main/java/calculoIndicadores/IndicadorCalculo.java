package calculoIndicadores;

import domain.Empresa;
import domain.repositorios.RepositorioIndicadores;
import interfaces.Indicador;

public class IndicadorCalculo extends Token {
	private String indicador;
	
	public IndicadorCalculo(String indicador){
		this.prioridad = 0;
		this.indicador = indicador;
	}
	
	public String getIndicador(){
		return this.indicador;
	}
	 
	public Double calcularValor(Empresa empresa, String periodo) {
		Indicador indicador = RepositorioIndicadores.instance().buscarIndicador(this.indicador);
		return indicador.calcularIndicador(empresa, periodo);
	}
	
	
}
