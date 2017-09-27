package domain.indicadores.indicadoresPredeterminados;

import javax.persistence.Entity;

import domain.Empresa;
import domain.indicadores.IndicadorPredeterminado;

@Entity
public class Leverage extends IndicadorPredeterminado{
	
	public Leverage() {
		setNombre("Leverage");
	}
	
	@Override
	public Double calcularIndicador(Empresa empresa, String periodo) {
		return valorDelIndicador("ROE",empresa,periodo) / valorDelIndicador("ROA",empresa,periodo);
	}
}
