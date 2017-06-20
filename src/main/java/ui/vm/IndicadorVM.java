package ui.vm;

import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import interfaces.Indicador;

@Observable
public class IndicadorVM {
	private Indicador indicador;
	private Empresa empresa;
	private String periodo;
	
	
	public IndicadorVM(Indicador indicador, Empresa empresa, String periodo) {
		this.indicador = indicador;
		this.empresa = empresa;
		this.periodo = periodo;
	}

	public Double getValor() {
		return indicador.calcularIndicador(empresa, periodo);
	}

	public String getNombre() {
		return indicador.getNombre();
	}
	
}
