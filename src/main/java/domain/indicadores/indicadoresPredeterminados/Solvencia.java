package domain.indicadores.indicadoresPredeterminados;

import domain.Empresa;
import domain.indicadores.IndicadorPredeterminado;

public class Solvencia extends IndicadorPredeterminado{

	public Solvencia() {
		setNombre("Solvencia");
	}
	
	public String getEcuacion() {
		return "ActivoTotal/PasivoTotal";
	}
	
	public boolean esCalculable(Empresa empresa, String periodo) {
		return cuentaCalculable("ActivoTotal",empresa, periodo) && cuentaCalculable("PasivoTotal",empresa, periodo);
	}

	@Override
	public Double calcularIndicador(Empresa empresa, String periodo) {
		return valorDeLaCuenta("ActivoTotal", empresa, periodo) /  valorDeLaCuenta("PasivoTotal", empresa,periodo);
	}
}