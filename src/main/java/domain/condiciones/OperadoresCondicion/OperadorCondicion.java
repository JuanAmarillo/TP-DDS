package domain.condiciones.OperadoresCondicion;

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
