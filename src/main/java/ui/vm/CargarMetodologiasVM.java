package ui.vm;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import domain.condiciones.Condicion;
import domain.metodologias.AplicadorDeCondiciones;
import domain.metodologias.Metodologia;
import domain.repositorios.RepositorioCondiciones;
import domain.repositorios.RepositorioMetodologias;

@Observable
public class CargarMetodologiasVM {

	public String nombreMetodologia;

	public List<String> listaCondiciones;
	public String condicionSeleccionada;
	
	public List<String> condicionesAAgregar;
	public String condicionAAgregarSeleccionada;
	
	public Metodologia ultimaCapa;
	
	public CargarMetodologiasVM() {
		listaCondiciones = RepositorioCondiciones.instance().getNombresDeCondiciones();
		condicionesAAgregar = new ArrayList<String>();
		inicializarStrings();
	}
	
	private void inicializarStrings() {
		condicionSeleccionada = "";
		condicionAAgregarSeleccionada = "";
	}

	public void cargarMetodologia() {
		realizarValidaciones();
		prepararMetodologia();
		ultimaCapa.setNombre(nombreMetodologia);
		RepositorioMetodologias.instance().agregarMetodologia(ultimaCapa);
	}
			
	private void prepararMetodologia() {
		condicionesAAgregar.stream().forEach(cond -> agregarCapa(cond));
	}
	
	private void agregarCapa(String nombreCond) {
		Condicion cond = RepositorioCondiciones.instance().buscarCondicion(nombreCond).get();
		ultimaCapa = new AplicadorDeCondiciones(ultimaCapa, cond);
	}

	private void realizarValidaciones() {
		validarNombre();
 		validarQueHayaAlgunaCondicion();
	}
	
	private void validarNombre() {
			if (nombreMetodologia.isEmpty())
				throw new RuntimeException("No se ingreso un nombre para la metodologia");
	}
	
	private void validarQueHayaAlgunaCondicion() {
		if(condicionesAAgregar.size() == 0)
			throw new RuntimeException("No se seleccionó ninguna condición");
	}
	
	private void actualizarListas() {
		ObservableUtils.firePropertyChanged(this, "listaCondiciones");
		ObservableUtils.firePropertyChanged(this, "condicionesAAgregar");		
	}
	
	public void moverHaciaLaIzquierda() {
		String valor = condicionAAgregarSeleccionada;
		if(valor != null) {
			condicionesAAgregar.remove(valor);
			listaCondiciones.add(valor);
			actualizarListas();
		}
	}

	public void moverHaciaLaDerecha() {
		String valor = condicionSeleccionada;
		if(valor != null) {
			condicionesAAgregar.add(valor);
			listaCondiciones.remove(valor);
			actualizarListas();
		}
	}
	
	// GETTERS Y SETTERS
	
	public String getNombreMetodologia() {
		return nombreMetodologia;
	}
	
	public void setNombreMetodologia(String nombreMetodologia) {
		this.nombreMetodologia = nombreMetodologia;
	}

	public List<String> getListaCondiciones() {
		return listaCondiciones;
	}

	public void setListaCondiciones(List<String> listaCondiciones) {
		this.listaCondiciones = listaCondiciones;
	}

	public String getCondicionSeleccionada() {
		return condicionSeleccionada;
	}

	public void setCondicionSeleccionada(String condicionSeleccionada) {
		this.condicionSeleccionada = condicionSeleccionada;
	}

	public List<String> getCondicionesAAgregar() {
		return condicionesAAgregar;
	}

	public void setCondicionesAAgregar(List<String> condicionesAAgregar) {
		this.condicionesAAgregar = condicionesAAgregar;
	}

	public String getCondicionAAgregarSeleccionada() {
		return condicionAAgregarSeleccionada;
	}

	public void setCondicionAAgregarSeleccionada(String condicionAAgregarSeleccionada) {
		this.condicionAAgregarSeleccionada = condicionAAgregarSeleccionada;
	}

	
}
