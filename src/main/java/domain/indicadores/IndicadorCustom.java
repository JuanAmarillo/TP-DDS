package domain.indicadores;

import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.indicadores.calculoIndicadores.Calculable;
import domain.indicadores.calculoIndicadores.Token;
import domain.indicadores.calculoIndicadores.ConstructoresIndicador.Analizador;

@Observable
public class IndicadorCustom extends Indicador {
	public String expresion;

	public Calculable calculo;

	public IndicadorCustom(String nombre,String expresion,Calculable calculo) {
		super(nombre);
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
	
	public String getExpresion() {
		return expresion;
	}

	public void setExpresion(String expresion) {
		this.expresion = expresion;
	}
	
	@Override
	public boolean esCustom() {
		return true;
	}

	

}
