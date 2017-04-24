package domain;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class Empresa {
	protected DateTime periodo;
	private List<Cuenta> cuentas = new ArrayList<>();
	private List<Indicador> indicadores = new ArrayList<>();

	public DateTime getPeriodo() {
		return periodo;
	}

	public void setPeriodo(DateTime periodo) {
		this.periodo = periodo;
	}

	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

}
