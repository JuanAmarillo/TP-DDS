package domain.indicadores;

import java.util.Optional;

import org.uqbar.commons.utils.Observable;

@Observable
public class IndicadorCalculado {
	private Optional<Double> valorExito;
	private String nombreIndicador;

	private void setIndicadorCalculado(String nombre,Optional<Double> valorExito) {
		this.valorExito = valorExito;;
		this.nombreIndicador = nombre;
	}

	public IndicadorCalculado(String nombre,Double value) {
		setIndicadorCalculado(nombre,Optional.of(value));
	}

	public IndicadorCalculado(String nombre) {
		setIndicadorCalculado(nombre,Optional.empty());
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
		return nombreIndicador;
	}

}
