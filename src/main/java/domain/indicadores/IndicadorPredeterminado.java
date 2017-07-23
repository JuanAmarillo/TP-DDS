package domain.indicadores;

import domain.Empresa;
import domain.repositorios.RepositorioIndicadores;

public abstract class IndicadorPredeterminado extends Indicador {

	@Override
	public boolean esCustom() {
		return false;
	}

	protected boolean indicadorCalculable(String indicador, Empresa empresa, String periodo) {
		return existeIndicador(indicador);
	}

	protected boolean cuentaCalculable(String indicador, Empresa empresa, String periodo) {
		return empresa.contieneLaCuentaDePeriodo(indicador, periodo);
	}

	protected Indicador buscarIndicador(String indicador) {
		return RepositorioIndicadores.instance().buscarIndicador(indicador).get();
	}

	private boolean existeIndicador(String indicador) {
		return RepositorioIndicadores.instance().contieneElIndicador(indicador);
	}

	protected Double valorDelIndicador(String indicador, Empresa empresa, String periodo) {
		return buscarIndicador(indicador).calcularIndicador(empresa, periodo);
	}

	protected double valorDeLaCuenta(String cuenta, Empresa empresa, String periodo) {
		return empresa.getValorDeLaCuenta(cuenta, periodo);
	}

}
