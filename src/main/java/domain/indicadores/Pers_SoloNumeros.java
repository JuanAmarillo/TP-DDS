package domain.indicadores;

import domain.Empresa;
import interfaces.IndicadorPredeterminado;

public class Pers_SoloNumeros extends IndicadorPredeterminado{

	public Pers_SoloNumeros() {
		setNombreIndicador("PruebaSoloNumeros");
	}
	
	@Override
	public Double calcularIndicador(Empresa emp, String periodo) {
		asignarAVariables(emp,periodo);
		return 23.0;
	}

	@Override
	public String getEcuacion() {
		return "23.0";
	}

	private void asignarAVariables(Empresa emp, String periodo) {
	}	
}
