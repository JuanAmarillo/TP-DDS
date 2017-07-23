package domain.indicadores.indicadoresPredeterminados;

import domain.Empresa;
import domain.indicadores.IndicadorPredeterminado;

public class ROA extends IndicadorPredeterminado{

	public ROA() {
		setNombre("ROA");
	}
	
	public String getEcuacion() {
		return "BeneficioEconomico/ActivoTotal";
	}

	@Override
	public Double calcularIndicador(Empresa empresa, String periodo) {
		return valorDeLaCuenta("BeneficioEconomico", empresa, periodo) /  valorDeLaCuenta("ActivoTotal", empresa,periodo);
	}
}
