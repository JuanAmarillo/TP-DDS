package domain.indicadores.indicadoresPredeterminados;

import javax.persistence.Entity;

import domain.Empresa;
import domain.indicadores.IndicadorPredeterminado;

@Entity
public class RAC extends IndicadorPredeterminado{

	public RAC() {
		setNombre("Rotacion de Activo Corriente");
	}

	@Override
	public Double calcularIndicador(Empresa empresa, String periodo) {
		return valorDeLaCuenta("Ventas", empresa, periodo) /  valorDeLaCuenta("ActivoCorriente", empresa,periodo);
	}
}