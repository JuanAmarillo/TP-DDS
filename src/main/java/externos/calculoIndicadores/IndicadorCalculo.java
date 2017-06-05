package externos.calculoIndicadores;

import domain.Empresa;
import domain.repositorios.RepositorioIndicadores;

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
		//return RepositorioIndicadores.instance().getValorDelIndicador(empresa, indicador, periodo);
		return 10.0;
	}
	
	
}
