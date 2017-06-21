package domain.indicadores.indicadoresPredeterminados;

import domain.Empresa;
import domain.indicadores.IndicadorPredeterminado;

public class ROE extends IndicadorPredeterminado{

	public ROE() {
		setNombreIndicador("ROE");
	}
	
	public String getEcuacion() {
		return "Beneficio/PatrimonioNeto";
	}

	public boolean esCalculable(Empresa empresa, String periodo) {
		return empresa.contieneLaCuentaDePeriodo("BeneficioEconomico", periodo) && 
				empresa.contieneLaCuentaDePeriodo("ActivoTotal", periodo);
	}
	
	@Override
	public Double calcularIndicador(Empresa empresa, String periodo) {
		return valorDeLaCuenta("Beneficio", empresa, periodo) / valorDeLaCuenta("PatrimonioNeto", empresa, periodo);
	}
}
