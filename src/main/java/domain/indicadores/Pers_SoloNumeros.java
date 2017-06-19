package domain.indicadores;

import domain.Empresa;
import interfaces.IndicadorPredeterminado;

public class Pers_SoloNumeros extends IndicadorPredeterminado{

	public Pers_SoloNumeros() {
		setNombreIndicador("PruebaSoloNumeros");
	}
	
	public Double calculo() {
		return 23.0;
	}	
	
	public boolean esCalculable(Empresa empresa, String periodo) {
		return true;
	}

	public void asignarAVariables(Empresa emp, String periodo) {
	}	
	
	@Override
	public String getEcuacion() {
		return "23.0";
	}
}
