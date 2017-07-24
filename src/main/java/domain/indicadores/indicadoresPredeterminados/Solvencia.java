package domain.indicadores.indicadoresPredeterminados;

import domain.Empresa;
import domain.indicadores.IndicadorPredeterminado;

public class Solvencia extends IndicadorPredeterminado{

	public Solvencia() {
		setNombre("Solvencia");
	}

	@Override
	public Double calcularIndicador(Empresa empresa, String periodo) {
		return valorDeLaCuenta("ActivoTotal", empresa, periodo) /  valorDeLaCuenta("PasivoTotal", empresa,periodo);
	}
}