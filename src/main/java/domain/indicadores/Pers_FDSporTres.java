package domain.indicadores;

import domain.Empresa;
import interfaces.IndicadorPredeterminado;

public class Pers_FDSporTres extends IndicadorPredeterminado{
	
	private double FDS;

	public Pers_FDSporTres() {
		setNombreIndicador("FDS por 3");
	}
	
	public Double calculo() {
		return FDS*3;
	}	
	
	public boolean esCalculable(Empresa empresa, String periodo) {
		return empresa.contieneLaCuentaDePeriodo("FDS", periodo);
	}

	public void asignarAVariables(Empresa empresa, String periodo) {
		FDS = empresa.getValorDeLaCuenta("FDS", periodo);
	}	
	
	@Override
	public String getEcuacion() {
		return "FDS*3";
	}
}
