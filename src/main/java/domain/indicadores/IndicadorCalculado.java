package domain.indicadores;

import java.util.Optional;

import org.hibernate.annotations.Entity;
import org.uqbar.commons.utils.Observable;

import domain.Empresa;

@Entity
public class IndicadorCalculado {
	private Optional<Double> valorExito;
	private Empresa empresa;
	private String periodo;
	private Indicador indicador;

	private void setIndicadorCalculado(Indicador indicador,Empresa empresa, String periodo, Optional<Double> valorExito) {
		this.valorExito = valorExito;
		this.empresa = empresa;
		this.periodo = periodo;
		this.indicador = indicador;
	}

	public IndicadorCalculado(Indicador indicador, Empresa empresa, String periodo, Double value) {
		setIndicadorCalculado(indicador,empresa, periodo, Optional.of(value));
	}

	public IndicadorCalculado(Indicador indicador, Empresa empresa, String periodo) {
		setIndicadorCalculado(indicador,empresa, periodo, Optional.empty());
	}

	public String getValorString() {
		return getValorExito().orElse(getValorFalla());
	}

	public Optional<String> getValorExito() {
		return valorExito.map(valor-> valor.toString());
	}

	public String getValorFalla() {
		return "No pudo calcularse";
	}
	
	public String getNombre(){
		return indicador.getNombre();
	}
}
