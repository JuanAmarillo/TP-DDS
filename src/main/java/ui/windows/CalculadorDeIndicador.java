package ui.windows;

import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.indicadores.Indicador;

@Observable
public class CalculadorDeIndicador {
	private Indicador indicador;
	private Double valor;

	public CalculadorDeIndicador(Indicador indicador,Empresa empresa, String periodo) {
		this.indicador = indicador;
		this.valor = indicador.calcularIndicador(empresa, periodo);
	}

	public Double getValor() {
		return valor;
	}

	public String getNombre() {
		return indicador.getNombre();
	}

}
