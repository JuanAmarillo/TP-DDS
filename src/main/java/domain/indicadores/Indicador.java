package domain.indicadores;

import domain.Empresa;

public abstract class Indicador {
	protected String nombre;

	public boolean suNombreEs(String nombreIndicador){
		return this.nombre.equals(nombreIndicador);
	}

	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	abstract public Double calcularIndicador(Empresa empresa, String periodo);
	
	abstract public boolean esCustom();

	abstract public Object getEcuacion();
	
	abstract public boolean esCalculable(Empresa empresa, String periodo);

}
