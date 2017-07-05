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
	
	public boolean esCalculable(Empresa empresa, String periodo) {
		return cuentaCalculable("ActivoTotal",empresa, periodo) && cuentaCalculable("PasivoTotal",empresa, periodo);
	}

	@Override
	public Double calcularIndicador(Empresa empresa, String periodo) {
		return valorDeLaCuenta("PasivoTotal", empresa,periodo) / valorDeLaCuenta("ActivoTotal", empresa, periodo);
	}
}