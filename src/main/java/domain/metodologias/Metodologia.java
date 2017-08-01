package domain.metodologias;

import java.util.List;

import org.uqbar.commons.utils.Observable;
import domain.condiciones.Condicion;

@Observable
public class Metodologia {
	
	private String nombre;
	private List<Condicion> condiciones;
	
	public Metodologia(String nombre, List<Condicion> condiciones) {
		this.nombre = nombre;
		this.condiciones = condiciones;
	}

	public boolean suNombreEs(String nombre){
		return this.nombre.equals(nombre);
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}

	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}
}
