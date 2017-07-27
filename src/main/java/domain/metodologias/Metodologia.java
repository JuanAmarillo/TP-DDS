package domain.metodologias;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.condiciones.Condicion;
import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionTaxativa;

@Observable
public class Metodologia {
	
	private String nombre;
	private List<Condicion> condiciones;
	
	public Metodologia(String nombre, List<CondicionTaxativa> condicionesTaxativasAAgregar,	List<CondicionComparativa> condicionesComparativasAAgregar) {
		super();
		this.nombre = nombre;
		//para q funcione por ahora
		this.condiciones = new ArrayList<>();
		this.condiciones.addAll(condicionesTaxativasAAgregar);
		this.condiciones.addAll(condicionesComparativasAAgregar);
	}

	public boolean suNombreEs(String nombre){
		return this.nombre.equals(nombre);
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return this.nombre;
	}

	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}
}
