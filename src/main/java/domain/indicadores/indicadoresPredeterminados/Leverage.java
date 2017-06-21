package domain.indicadores.indicadoresPredeterminados;

import domain.Empresa;
import domain.indicadores.IndicadorPredeterminado;

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
	
	public String getEcuacion() {
		return "ROE/ROA";
	}
}
