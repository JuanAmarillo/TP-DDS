package domain.indicadores.indicadoresPredeterminados;

import domain.Empresa;
import domain.indicadores.IndicadorPredeterminado;

public class ROA extends IndicadorPredeterminado{
	private double BeneficioEconomico;
	private double ActivoTotal;

	public ROA() {
		setNombreIndicador("ROA");
	}
	
	public Double calculo() {
		return BeneficioEconomico/ActivoTotal;
	}	
	
	public boolean esCalculable(Empresa empresa, String periodo) {
		return cuentaCalculable("BeneficioEconomico",empresa, periodo) && cuentaCalculable("ActivoTotal",empresa, periodo);
	}


	public void asignarAVariables(Empresa empresa, String periodo) {
		BeneficioEconomico = valorDeLaCuenta("BeneficioEconomico", empresa, periodo);
		ActivoTotal = valorDeLaCuenta("ActivoTotal", empresa,periodo);
	}	
	
	public String getEcuacion() {
		return "BeneficioEconomico/ActivoTotal";
	}
}
