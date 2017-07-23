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
	
	public EitherIndicador calcular(Empresa empresa, String periodo){
		try{
			return new EitherIndicador(nombre, calcularIndicador(empresa, periodo));
		}catch (RuntimeException e) {
			return new EitherIndicador(nombre);
		}
	}
	
	abstract public Double calcularIndicador(Empresa empresa, String periodo);
	
	abstract public boolean esCustom();

	abstract public String getEcuacion();
	
	abstract public boolean esCalculable(Empresa empresa, String periodo);

}
