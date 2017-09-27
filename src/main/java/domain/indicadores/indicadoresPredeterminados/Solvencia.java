package domain.indicadores.indicadoresPredeterminados;

import javax.persistence.Entity;

import domain.Empresa;
import domain.indicadores.IndicadorPredeterminado;

@Entity
public class Solvencia extends IndicadorPredeterminado{

	public Solvencia() {
		setNombre("Solvencia");
	}

	@Override
	public Double calcularIndicador(Empresa empresa, String periodo) {
		return valorDeLaCuenta("ActivoTotal", empresa, periodo) /  valorDeLaCuenta("PasivoTotal", empresa,periodo);
	}
}