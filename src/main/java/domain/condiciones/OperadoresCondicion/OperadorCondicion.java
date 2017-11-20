package domain.condiciones.OperadoresCondicion;

import javax.persistence.MappedSuperclass;

import org.uqbar.commons.utils.Observable;

@MappedSuperclass
public abstract class OperadorCondicion {
	protected String nombreDelOperador;
	
	public OperadorCondicion() {}
	
	public OperadorCondicion(String nombre){
		this.nombreDelOperador = nombre;
	}
	
	public String getNombre(){
		return nombreDelOperador;
	}
	public abstract int comparar(Double valorIndicadorUno,Double valorIndicadorDos);
}