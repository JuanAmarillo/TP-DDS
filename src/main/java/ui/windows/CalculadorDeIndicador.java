package ui.windows;

import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.indicadores.Indicador;

@Observable
public class CalculadorDeIndicador {
	private Indicador indicador;
	private Double valor;
	
	public CalculadorDeIndicador(Indicador indicador){
		this.indicador=indicador;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Empresa empresa, String periodo) {
		this.valor = indicador.calcularIndicador(empresa, periodo);
	}
	public String getNombre() {
		return indicador.getNombre();
	}
	

}
