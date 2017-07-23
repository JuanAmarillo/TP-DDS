package domain.indicadores.indicadoresPredeterminados;

import domain.Empresa;
import domain.indicadores.IndicadorPredeterminado;

public class RAC extends IndicadorPredeterminado{

	public RAC() {
		setNombre("Rotacion de Activo Corriente");
	}
	
	public String getEcuacion() {
		return "Ventas/ActivoCorriente";
	}

	@Override
	public Double calcularIndicador(Empresa empresa, String periodo) {
		return valorDeLaCuenta("Ventas", empresa, periodo) /  valorDeLaCuenta("ActivoCorriente", empresa,periodo);
	}
}