package domain.metodologias;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.condiciones.Condicion;
import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionTaxativa;

@Observable
public class Metodologia {
	
	private String nombre;
	private List<CondicionTaxativa> condicionesTaxativas;
	private List<CondicionComparativa> condicionesComparativas;
	
	public boolean suNombreEs(String nombre){
		return this.nombre.equals(nombre);
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return this.nombre;
	}

	public List<CondicionTaxativa> getCondicionesTaxativas() {
		return condicionesTaxativas;
	}

	public void setCondicionesTaxativas(List<CondicionTaxativa> condicionesTaxativas) {
		this.condicionesTaxativas = condicionesTaxativas;
	}

	public List<CondicionComparativa> getCondicionesComparativas() {
		return condicionesComparativas;
	}

	public void setCondicionesComparativas(List<CondicionComparativa> condicionesComparativas) {
		this.condicionesComparativas = condicionesComparativas;
	}

}
