package externos.calculoIndicadores;

import domain.Empresa;
import domain.repositorios.RepositorioIndicadores;

public class IndicadorCalculo implements Token {
	private String indicador;
	
	public IndicadorCalculo(String indicador){
		this.indicador = indicador;
	}
	
	public double calcularValor(Empresa empresa, String periodo) {
		return RepositorioIndicadores.instance().buscarIndicador(indicador).calcularIndicador(empresa, periodo);
	}
	
	
}
