package domain.indicadores;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
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

	@JsonCreator
	private IndicadorCustom( @JsonProperty("nombre") String nombre,@JsonProperty("expresion")String expresion ) {
		super(nombre);
		this.expresion = expresion;
		//setCalculo();
		}

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
	
	@Override
	public boolean esCustom() {
		return true;
	}

}
