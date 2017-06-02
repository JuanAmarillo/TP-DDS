package domain.indicadores;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.uqbar.commons.utils.Observable;

import domain.Empresa;

import interfaces.Indicador;

import externos.AnalizadorDeIndicadores;

@Observable
public class IndicadorCustom implements Indicador{
	public String nombre;
	public String ecuacion;

	@JsonIgnore
	public Double valor;
	
	// METODO PARA RESPETAR LA INTERFACE
	public Double calcularIndicador(Empresa empresa, String periodo) {
		return new AnalizadorDeIndicadores(empresa, periodo).scan(this).parser();
	}
	
	public boolean suNombreEs(String indicador) {
		return this.nombre.equals(indicador);
	}
	
	public static String getNombre(String indicador){
		String[] partesDelIndicador = separarIndicadorEnPartes(indicador);
		return partesDelIndicador[0].trim();
	}
	
	public static String getEcuacion(String indicador){
		String[] partesDelIndicador = separarIndicadorEnPartes(indicador);
		return partesDelIndicador[1];
	}
	
	public static String[] separarIndicadorEnPartes(String indicador){
		String[] partesDelIndicador =  indicador.split("=");
		return indicadorSiTieneUnaAsignacion(partesDelIndicador);
	}
	
	private static String[] indicadorSiTieneUnaAsignacion(String[] partesDelIndicador) {
		if(partesDelIndicador.length == 2)
			return partesDelIndicador;
		else
			throw new RuntimeException("El indicador debe tener solo una asignacion");
	}

	public static IndicadorCustom armarApartirDe(String indicador){
		IndicadorCustom unIndicador = new IndicadorCustom();
		unIndicador.setNombreIndicador(getNombre(indicador));
		unIndicador.setEcuacion(getEcuacion(indicador));
		return unIndicador;
	}

	public void ecuacionContieneAlNombre(){
		if(ecuacion.contains(nombre))
			throw new RuntimeException("El indicador no puede llamarse a si mismo");
	}
	
	// SETTERS Y GETTERS //
	
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
		
		public Double getValor() {
			return valor;
		}
		
		public void setValor(Empresa empresa,String periodo) {
			this.valor = new AnalizadorDeIndicadores(empresa,periodo).scan(this).parser();
		}

		@Override
		public boolean esCustom() {
			return true;
		}

}