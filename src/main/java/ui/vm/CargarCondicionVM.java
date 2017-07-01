package ui.vm;

import org.uqbar.arena.widgets.List;

public class CargarCondicionVM {

	public String condicion;
	public List<String> condiciones;
	public String condicionSeleccionada;
	
	public String getCondicion() {
		return condicion;
	}
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	public List<String> getCondiciones() {
		return condiciones;
	}
	
	public String getCondicionSeleccionada() {
		return condicionSeleccionada;
	}
	public void setCondicionSeleccionada(String condicionSeleccionada) {
		this.condicionSeleccionada = condicionSeleccionada;
	}
	
	
	
	
}
