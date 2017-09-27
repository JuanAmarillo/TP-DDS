package domain.indicadores.indicadoresPredeterminados;

import javax.persistence.Entity;

import domain.Empresa;
import domain.indicadores.IndicadorPredeterminado;

@Entity
public class ROA extends IndicadorPredeterminado{

	public ROA() {
		setNombre("ROA");
	}

	@Override
	public Double calcularIndicador(Empresa empresa, String periodo) {
		return valorDeLaCuenta("BeneficioEconomico", empresa, periodo) /  valorDeLaCuenta("ActivoTotal", empresa,periodo);
	}
}
