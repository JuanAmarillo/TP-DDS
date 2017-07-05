package domain.indicadores;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.uqbar.commons.utils.Observable;

import calculoIndicadores.ConstructoresIndicador.Analizador;
import calculoIndicadores.Calculable;
import calculoIndicadores.Token;
import domain.Empresa;

@Observable
public class IndicadorCustom extends Indicador {
	public String ecuacion;
	@JsonIgnore
	public Calculable calculo;

	public IndicadorCustom() {
	}

	public Double calcularIndicador(Empresa empresa, String periodo) {
		return calculo.calcularValor(empresa, periodo);
	}

	public boolean esCalculable(Empresa empresa, String periodo) {
		return new Analizador(ecuacion).sePuedeCalcular(empresa, periodo);
	}

	public Calculable generarCalculo() {
		return new Analizador(ecuacion).compilar();
	}

	// SETTERS Y GETTERS //

	public void setCalculo() {
		this.calculo = generarCalculo();
	}

	public String getEcuacion() {
		return ecuacion;
	}

	public void setEcuacion(String ecuacion) {
		this.ecuacion = ecuacion;
	}

	public Token getCalculo() {
		return calculo;
	}

	@Override
	public boolean esCustom() {
		return true;
	}

}
