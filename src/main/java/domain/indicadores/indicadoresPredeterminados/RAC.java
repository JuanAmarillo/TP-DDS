package domain.indicadores.indicadoresPredeterminados;

import domain.Empresa;
import domain.indicadores.IndicadorPredeterminado;

public class RAC extends IndicadorPredeterminado{

	public RAC() {
		setNombre("Rotacion de Activo Corriente");
	}
	
	public String getEcuacion() {
		return "Ventas/ActivoCte";
	}
	
	public boolean esCalculable(Empresa empresa, String periodo) {
		return cuentaCalculable("Activo Cte",empresa, periodo) && cuentaCalculable("Ventas",empresa, periodo);
	}

	@Override
	public Double calcularIndicador(Empresa empresa, String periodo) {
		return valorDeLaCuenta("Ventas", empresa, periodo) /  valorDeLaCuenta("Activo Cte", empresa,periodo);
	}
}