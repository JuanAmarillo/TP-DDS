package domain.indicadores;


import org.codehaus.jackson.annotate.JsonIgnore;
import org.uqbar.commons.utils.Observable;

import calculoIndicadores.ConstructoresIndicador.Analizador;
import calculoIndicadores.Token;
import domain.Empresa;

import interfaces.Indicador;


@Observable
public class IndicadorCustom implements Indicador{
	public String nombre;
	public String ecuacion;
	@JsonIgnore
	public Token calculo;

	
	
	public IndicadorCustom(String indicador){
		analizarSintacticamente(indicador);
		this.setNombreIndicador(generarNombre(indicador));
		this.setEcuacion(generarEcuacion(indicador));
		this.setCalculo();
	}
	
	public IndicadorCustom(){}
	
	public Double calcularIndicador(Empresa empresa, String periodo) {
		return calculo.calcularValor(empresa, periodo);
	}
	
	private Boolean analizarSintacticamente(String indicador) {
		return new Analizador().scan(indicador).parser();
	}
	
	public boolean esCalculable(Empresa empresa, String periodo) {
		return new Analizador().scan(ecuacion).sePuedeCalcular(empresa,periodo);
	}
	
	public boolean suNombreEs(String indicador) {
		return this.nombre.equals(indicador);
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
	
	public Token generarCalculo(){
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

	public String getNombre() {
		return nombre;
	}

	public void setNombreIndicador(String nombre) {
		this.nombre = nombre;
	}
		
	public Token getCalculo() {
		return calculo;
	}
			
	@Override
	public boolean esCustom() {
		return true;
	}

}
