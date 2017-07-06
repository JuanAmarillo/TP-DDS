package domain.condiciones.OperadoresCondicion;

import org.uqbar.commons.utils.Observable;

@Observable
public abstract class OperadorCondicion {
	protected String nombre;
	
	public OperadorCondicion(String nombre){
		this.nombre = nombre;
	}
	
	public String getNombre(){
		return nombre;
	}
	public abstract int comparar(Double valorIndicadorUno,Double valorIndicadorDos);
}
