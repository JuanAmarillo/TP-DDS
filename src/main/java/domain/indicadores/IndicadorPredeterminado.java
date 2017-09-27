package domain.indicadores;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import domain.Empresa;
import domain.repositorios.RepositorioIndicadores;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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
