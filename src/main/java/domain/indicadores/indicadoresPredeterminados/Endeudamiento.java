package domain.indicadores.indicadoresPredeterminados;

import domain.Empresa;
import domain.indicadores.IndicadorPredeterminado;

public class Endeudamiento extends IndicadorPredeterminado{

	public Endeudamiento() {
		setNombre("Endeudamiento");
	}
	
	@Override
	public Double calcularIndicador(Empresa empresa, String periodo) {
		return valorDeLaCuenta("PasivoTotal", empresa,periodo) / valorDeLaCuenta("ActivoTotal", empresa, periodo);
	}
}