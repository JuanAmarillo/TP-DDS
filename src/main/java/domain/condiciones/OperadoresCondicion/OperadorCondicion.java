package domain.condiciones.OperadoresCondicion;

import javax.persistence.Embeddable;

import org.uqbar.commons.utils.Observable;

@Observable
@Embeddable
public abstract class OperadorCondicion {
	protected String nombre;
	
	public OperadorCondicion() {}
	
	public OperadorCondicion(String nombre){
		this.nombre = nombre;
	}
	
	public String getNombre(){
		return nombre;
	}
	public abstract int comparar(Double valorIndicadorUno,Double valorIndicadorDos);
}