package domain.indicadores;


import org.codehaus.jackson.annotate.JsonIgnore;
import org.uqbar.commons.utils.Observable;

import calculoIndicadores.ConstructoresIndicador.Analizador;
import calculoIndicadores.Calculable;
import calculoIndicadores.Token;
import domain.Empresa;


@Observable
public class IndicadorCustom extends Indicador{
	public String ecuacion;
	@JsonIgnore
	public Calculable calculo;

	
	
	public IndicadorCustom(String indicador){
		analizarSintacticamente(indicador);
		this.setNombre(generarNombre(indicador));
		this.setEcuacion(generarEcuacion(indicador));
		this.setCalculo();
	}
	
	public IndicadorCustom(){}
	
	public Double calcularIndicador(Empresa empresa, String periodo) {
		return calculo.calcularValor(empresa, periodo);
	}
	
	private void analizarSintacticamente(String indicador) {
		 new Analizador().scan(indicador).parser();
	}
	
	public boolean esCalculable(Empresa empresa, String periodo) {
		return new Analizador().scan(ecuacion).sePuedeCalcular(empresa,periodo);
	}
	
	
	public static String generarNombre(String indicador){
		String[] partesDelIndicador = separarIndicadorEnPartes(indicador);
		return partesDelIndicador[0].trim();
	}
	
	public static String generarEcuacion(String indicador){
		String[] partesDelIndicador = separarIndicadorEnPartes(indicador);
		return partesDelIndicador[1];
	}
	
	public static String[] separarIndicadorEnPartes(String indicador){
		return indicador.split("=");
	}
	
	public Calculable generarCalculo(){
		return new Analizador().scan(getEcuacion()).compilar();
	}
	
	// SETTERS Y GETTERS //
		
	public void setCalculo(){
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
