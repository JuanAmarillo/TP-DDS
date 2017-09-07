package domain.indicadores;

import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.indicadores.calculoIndicadores.Calculable;
import domain.indicadores.calculoIndicadores.Token;

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

	// SETTERS Y GETTERS //

	public void setCalculo(Calculable calculo) {
		this.calculo = calculo;
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
