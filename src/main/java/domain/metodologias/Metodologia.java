package domain.metodologias;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.condiciones.Condicion;

@Observable
public abstract class Metodologia {
	
	protected String nombre;
	
	public boolean suNombreEs(String nombre){
		return this.nombre.equals(nombre);
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public abstract Condicion getCondicionAAplicar();
	public abstract Metodologia getCadenaCondiciones();
	
	public String getNombre() {
		return this.nombre;
	}
	
	public abstract List<Empresa> aplicarMetodologia(List<Empresa> listaEmpresas, String periodo);
	
}
