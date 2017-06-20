package domain.indicadores;

import org.codehaus.jackson.annotate.JsonIgnore;
import java.util.EmptyStackException;

import org.uqbar.commons.utils.Observable;

import calculoIndicadores.ConstructoresIndicador.Analizador;
import calculoIndicadores.Token;
import domain.Empresa;

import interfaces.Indicador;


@Observable
public class IndicadorCustom implements Indicador{
	public String nombre;
	public String ecuacion;
	public Token calculo;

	@JsonIgnore
	
	public IndicadorCustom(String indicador){
		analizarSintacticamente(indicador);
		this.setNombreIndicador(generarNombre(indicador));
		this.setEcuacion(generarEcuacion(indicador));
		this.setCalculo(generarCalculo(indicador));
	}
	
	private Boolean analizarSintacticamente(String indicador) {
		return new Analizador().scan(indicador).parser();
	}
		
	// METODO PARA RESPETAR LA INTERFACE
	public Double calcularIndicador(Empresa empresa, String periodo) {
		return calculo.calcularValor(empresa, periodo);
	}
	
	public boolean esCalculable(Empresa empresa, String periodo) {
		try {
			calcularIndicador(empresa, periodo);
			return true;
		} catch (EmptyStackException e) {
			return false;
		}
		//return new Analizador(empresa, periodo).scan(ecuacion).sePuedeCalcular();
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
	
	public Token generarCalculo(String indicador){
		return new Analizador().scan(getEcuacion()).compilar();
	}
	
	// SETTERS Y GETTERS //
		
		public void setCalculo(Token calculo){
			this.calculo = calculo;
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
