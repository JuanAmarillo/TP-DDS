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

	public IndicadorCustom(){
		
	}
	
	public IndicadorCustom(String nombre,String ecuacion,Calculable calculo) {
		this.nombre   = nombre;
		this.ecuacion = ecuacion;
		this.calculo  = calculo;
	}

	public Double calcularIndicador(Empresa empresa, String periodo) {
		return calculo.calcularValor(empresa, periodo);
	}

	public boolean esCalculable(Empresa empresa, String periodo) {
		return new Analizador(ecuacion).sePuedeCalcular(empresa, periodo);
	}


	// SETTERS Y GETTERS //

	public String getEcuacion() {
		return ecuacion;
	}

	public Token getCalculo() {
		return calculo;
	}

	@Override
	public boolean esCustom() {
		return true;
	}

}
