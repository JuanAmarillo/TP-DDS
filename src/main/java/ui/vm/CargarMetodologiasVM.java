package ui.vm;



import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import domain.repositorios.RepositorioCondiciones;

@Observable
public class CargarMetodologiasVM {

	public String nombreMetodologia;
	
	public List<String> condiciones;
	public String condicionSeleccionada;
	
	
	public String condMetodologia;
	public List<String> condicionesMetodologia;
	
	public CargarMetodologiasVM() {
		condiciones = RepositorioCondiciones.instance().getNombresDeCondiciones();
		condicionesMetodologia = new ArrayList<String>();
	}
	
	public String getNombreMetodologia() {
		return nombreMetodologia;
	}
	public void setNombreMetodologia(String nombreMetodologia) {
		this.nombreMetodologia = nombreMetodologia;
	}
	public List<String> getCondiciones() {
		return condiciones;
	}
	public void setCondiciones(List<String> condiciones) {
		this.condiciones = condiciones;
	}
	public String getCondicionSeleccionada() {
		return condicionSeleccionada;
	}
	public void setCondicionSeleccionada(String condicionSeleccionada) {
		this.condicionSeleccionada = condicionSeleccionada;
	}
	public String getCondMetodologia() {
		return condMetodologia;
	}
	public void setCondMetodologia(String condMetodologia) {
		this.condMetodologia = condMetodologia;
	}
	public List<String> getCondicionesMetodologia() {
		return condicionesMetodologia;
	}
	public void setCondicionesMetodologia(List<String> condicionesMetodologia) {
		this.condicionesMetodologia = condicionesMetodologia;
	}



}
