package domain.indicadores.indicadoresPredeterminados;

import domain.Empresa;
import domain.indicadores.IndicadorPredeterminado;

public class ROE extends IndicadorPredeterminado{

	public ROE() {
		setNombre("ROE");
	}
	
	@Override
	public Double calcularIndicador(Empresa empresa, String periodo) {
		return valorDeLaCuenta("Beneficio", empresa, periodo) / valorDeLaCuenta("PatrimonioNeto", empresa, periodo);
	}
}
