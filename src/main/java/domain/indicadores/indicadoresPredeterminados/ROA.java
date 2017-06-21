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
		return empresa.contieneLaCuentaDePeriodo("BeneficioEconomico", periodo) && 
				empresa.contieneLaCuentaDePeriodo("ActivoTotal", periodo);
	}

	public void asignarAVariables(Empresa empresa, String periodo) {
		BeneficioEconomico = empresa.getValorDeLaCuenta("BeneficioEconomico", periodo);
		ActivoTotal = empresa.getValorDeLaCuenta("ActivoTotal", periodo);
	}	
	
	public String getEcuacion() {
		return "BeneficioEconomico/ActivoTotal";
	}
}
