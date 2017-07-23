package domain.indicadores;

import java.util.Optional;

import org.uqbar.commons.utils.Observable;

@Observable
public class EitherIndicador {
	private Optional<Double> valorExito;
	private Optional<String> valorFalla;
	private String nombreIndicador;

	private void setEitherIndicador(String nombre,Optional<Double> valorExito, Optional<String> valorFalla) {
		this.valorExito = valorExito;
		this.valorFalla = valorFalla;
		this.nombreIndicador = nombre;
	}

	public EitherIndicador(String nombre,Double value) {
		setEitherIndicador(nombre,Optional.of(value), Optional.empty());
	}

	public EitherIndicador(String nombre) {
		setEitherIndicador(nombre,Optional.empty(), Optional.of("No pudo calcularse"));
	}

	public String getValorString() {
		return valorExito.map(valor-> valor.toString()).orElseGet(valorFalla::get);
	}
	
	public String getNombre(){
		return nombreIndicador;
	}

}
