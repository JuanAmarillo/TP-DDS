package domain.indicadores;

import java.util.Optional;

public class EitherIndicador {

	private Optional<Double> valorExito;
	private Optional<String> valorFalla;

	private EitherIndicador(Optional<Double> valorExito, Optional<String> valorFalla) {
		this.valorExito = valorExito;
		this.valorFalla = valorFalla;
	}

	public static EitherIndicador left(Double value) {
		return new EitherIndicador(Optional.of(value), Optional.empty());
	}

	public static EitherIndicador right(String value) {
		return new EitherIndicador(Optional.empty(), Optional.of(value));
	}

	public String getValorString() {
		return valorExito.map(valor-> valor.toString()).orElseGet(valorFalla::get);
	}

}
