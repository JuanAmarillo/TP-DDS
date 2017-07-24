package domain.indicadores;

import domain.Empresa;
import domain.repositorios.RepositorioIndicadores;

public abstract class IndicadorPredeterminado extends Indicador {

	@Override
	public boolean esCustom() {
		return false;
	}

	protected Indicador buscarIndicador(String indicador) {
		return RepositorioIndicadores.instance().buscarIndicador(indicador).get();
	}

	protected Double valorDelIndicador(String indicador, Empresa empresa, String periodo) {
		return buscarIndicador(indicador).calcularIndicador(empresa, periodo);
	}

	protected double valorDeLaCuenta(String cuenta, Empresa empresa, String periodo) {
		return empresa.getValorDeLaCuenta(cuenta, periodo);
	}

}
