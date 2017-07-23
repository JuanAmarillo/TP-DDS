package domain.indicadores.indicadoresPredeterminados;

import domain.Empresa;
import domain.indicadores.IndicadorPredeterminado;

public class Endeudamiento extends IndicadorPredeterminado{

	public Endeudamiento() {
		setNombre("Indice de Endeudamiento");
	}
	
	public String getEcuacion() {
		return "PasivoTotal/ActivoTotal";
	}
	
	@Override
	public Double calcularIndicador(Empresa empresa, String periodo) {
		return valorDeLaCuenta("PasivoTotal", empresa,periodo) / valorDeLaCuenta("ActivoTotal", empresa, periodo);
	}
}