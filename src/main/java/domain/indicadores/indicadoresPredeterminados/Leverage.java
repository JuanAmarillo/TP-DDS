package domain.indicadores.indicadoresPredeterminados;

import domain.Empresa;
import domain.indicadores.Indicador;
import domain.indicadores.IndicadorPredeterminado;
import domain.repositorios.RepositorioIndicadores;

public class Leverage extends IndicadorPredeterminado{
	private double ROA;
	private double ROE;

	public Leverage() {
		setNombreIndicador("Leverage");
	}
	
	public Double calculo() {
		return ROE/ROA;
	}	
	
	public boolean esCalculable(Empresa empresa, String periodo) {
		return indicadorCalculable("ROE",empresa,periodo) && indicadorCalculable("ROA", empresa, periodo);
	}

	public void asignarAVariables(Empresa empresa, String periodo) {
		ROA = valorDelIndicador("ROA",empresa,periodo);
		ROE = valorDelIndicador("ROE",empresa,periodo);
	}
	
	protected double valorDelIndicador(String indicador, Empresa empresa, String periodo){
		return buscarIndicador(indicador).calcularIndicador(empresa, periodo);
	}
	
	public String getEcuacion() {
		return "ROE/ROA";
	}
}
