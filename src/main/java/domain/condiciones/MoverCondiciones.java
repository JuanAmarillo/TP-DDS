package domain.condiciones;

import java.util.List;

import ui.vm.VmUtils;

public abstract class MoverCondiciones<T> {
	
	private List<T> listaCondiciones;
	private T condicionSeleccionada;
	
	private List<T> listaCondicionesAAgregar;
	private T condicionAAgregarSeleccionada;
	
	public void moverHaciaLaIzquierda(){
		if(condicionAAgregarSeleccionada != null){
			listaCondiciones.add(condicionAAgregarSeleccionada);
			listaCondicionesAAgregar.remove(condicionAAgregarSeleccionada);
			condicionAAgregarSeleccionada = null;
			VmUtils.avisarCambios(this, "listaCondiciones", "listaCndicioneslistaCondicionesAAgregar");
		}
	}
	
	public void moverHaciaLaDerecha(){
		if(condicionSeleccionada != null){
			listaCondicionesAAgregar.add(condicionSeleccionada);
			listaCondiciones.remove(condicionSeleccionada);
			condicionSeleccionada = null;
			VmUtils.avisarCambios(this, "listaCondiciones", "listaCndicioneslistaCondicionesAAgregar");
		}
	}
}
