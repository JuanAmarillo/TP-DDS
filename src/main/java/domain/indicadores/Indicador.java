package domain.indicadores;

import org.codehaus.jackson.annotate.JsonProperty;

import domain.Empresa;

public abstract class Indicador {
	@JsonProperty("nombre")
	protected String nombre;
	
	public Indicador() {}
	
	public Indicador(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean suNombreEs(String nombreIndicador){
		return this.nombre.equals(nombreIndicador);
	}

	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public IndicadorCalculado calcular(Empresa empresa, String periodo){
		try{
			return new IndicadorCalculado(nombre, calcularIndicador(empresa, periodo));
		}catch (RuntimeException e) {
			return new IndicadorCalculado(nombre);
		}
	}
	
	abstract public Double calcularIndicador(Empresa empresa, String periodo);
	
	abstract public boolean esCustom();

//	abstract public String getEcuacion();
	

}
