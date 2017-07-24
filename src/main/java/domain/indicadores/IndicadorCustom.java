package domain.indicadores;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.indicadores.calculoIndicadores.Calculable;
import domain.indicadores.calculoIndicadores.Token;
import domain.indicadores.calculoIndicadores.ConstructoresIndicador.Analizador;

@Observable
public class IndicadorCustom extends Indicador {
	public String expresion;
	@JsonIgnore
	public Calculable calculo;

	public IndicadorCustom(){/*Para jackson*/}
	
	public IndicadorCustom(String nombre,String expresion,Calculable calculo) {
		this.nombre    = nombre;
		this.expresion = expresion;
		this.calculo   = calculo;
	}

	@Override
	public Double calcularIndicador(Empresa empresa, String periodo) {
		return calculo.calcularValor(empresa, periodo);			
	}

	public Calculable generarCalculo() {
		return new Analizador(expresion).compilar();
	}

	// SETTERS Y GETTERS //

	public void setCalculo() {
		this.calculo = generarCalculo();
	}

	public Token getCalculo() {
		return calculo;
	}

	@Override
	public boolean esCustom() {
		return true;
	}

}
