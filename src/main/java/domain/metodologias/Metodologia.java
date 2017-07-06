package domain.metodologias;

import java.util.List;

import domain.Empresa;

public abstract class Metodologia {
	
	protected String nombre;
	
	public boolean suNombreEs(String nombre){
		return this.nombre.equals(nombre);
	}
	
	public abstract List<Empresa> aplicarMetodologia(List<Empresa> listaEmpresas, String periodo);
	
}
