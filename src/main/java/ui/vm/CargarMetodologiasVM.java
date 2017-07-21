package ui.vm;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import domain.condiciones.Condicion;
import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionTaxativa;
import domain.metodologias.Metodologia;
import domain.repositorios.RepositorioCondiciones;
import domain.repositorios.RepositorioMetodologias;

@Observable
public class CargarMetodologiasVM {

	public String nombreMetodologia = "";

	public List<CondicionTaxativa> listaCondicionesTaxativas;
	public CondicionTaxativa condicionTaxativaSeleccionada = null;
	
	public List<CondicionTaxativa> condicionesTaxativasAAgregar;
	public CondicionTaxativa condicionTaxativaAAgregarSeleccionada = null;
	
	public List<CondicionComparativa> listaCondicionesComparativas;
	public CondicionComparativa condicionComparativaSeleccionada = null;
	
	public List<CondicionComparativa> condicionesComparativasAAgregar;
	public CondicionComparativa condicionComparativaAAgregarSeleccionada = null;
	
	public CargarMetodologiasVM() {
		listaCondicionesTaxativas = RepositorioCondiciones.instance().getCondicionesTaxativas();
		listaCondicionesComparativas = RepositorioCondiciones.instance().getCondicionesComparativas();
		condicionesTaxativasAAgregar = new ArrayList<CondicionTaxativa>();
		condicionesComparativasAAgregar = new ArrayList<CondicionComparativa>();
	}

	public void cargarMetodologia() {
		realizarValidaciones();
		RepositorioMetodologias.instance().agregarMetodologia(new Metodologia(nombreMetodologia,condicionesTaxativasAAgregar,condicionesComparativasAAgregar));
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
		if(condicionesTaxativasAAgregar.size() == 0 || condicionesComparativasAAgregar.size() == 0)
			throw new RuntimeException("No se seleccionó ninguna condición");
	}
	
	private void actualizarListas(String listaCondiciones, String condicionesAAgregar) {
		ObservableUtils.firePropertyChanged(this, listaCondiciones);
		ObservableUtils.firePropertyChanged(this, condicionesAAgregar);		
	}
	
	public void moverHaciaLaIzquierdaTaxativa() {
		if(condicionTaxativaAAgregarSeleccionada != null) {
			listaCondicionesTaxativas.add(condicionTaxativaAAgregarSeleccionada);
			condicionesTaxativasAAgregar.remove(condicionTaxativaAAgregarSeleccionada);
			condicionTaxativaAAgregarSeleccionada = null;
			actualizarListas("listaCondicionesTaxativas", "condicionesTaxativasAAgregar");
		}
	}	

	public void moverHaciaLaDerechaTaxativa() {
		if(condicionTaxativaSeleccionada != null) {
			condicionesTaxativasAAgregar.add(condicionTaxativaSeleccionada);
			listaCondicionesTaxativas.remove(condicionTaxativaSeleccionada);
			condicionTaxativaSeleccionada = null;
			actualizarListas("listaCondicionesTaxativas", "condicionesTaxativasAAgregar");
		}
	}
	
	public void moverHaciaLaDerechaComparativa() {
		if(condicionComparativaSeleccionada != null) {
			condicionesComparativasAAgregar.add(condicionComparativaSeleccionada);
			listaCondicionesComparativas.remove(condicionComparativaSeleccionada);
			condicionComparativaSeleccionada = null;
			actualizarListas("condicionesComparativasAAgregar", "listaCondicionesComparativas");
		}
	}

	public void moverHaciaLaIzquierdaComparativa() {
		if(condicionComparativaAAgregarSeleccionada != null) {
			listaCondicionesComparativas.add(condicionComparativaAAgregarSeleccionada);
			condicionesComparativasAAgregar.remove(condicionComparativaAAgregarSeleccionada);
			condicionComparativaAAgregarSeleccionada = null;
			actualizarListas("condicionesComparativasAAgregar", "listaCondicionesComparativas");
		}
	}
	
	// GETTERS Y SETTERS
	
	public String getNombreMetodologia() {
		return nombreMetodologia;
	}
	
	public void setNombreMetodologia(String nombreMetodologia) {
		this.nombreMetodologia = nombreMetodologia;
	}

	public List<CondicionTaxativa> getListaCondicionesTaxativas() {
		return listaCondicionesTaxativas;
	}

	public void setListaCondicionesTaxativas(List<CondicionTaxativa> listaCondicionesTaxativas) {
		this.listaCondicionesTaxativas = listaCondicionesTaxativas;
	}

	public CondicionTaxativa getCondicionTaxativaSeleccionada() {
		return condicionTaxativaSeleccionada;
	}

	public void setCondicionTaxativaSeleccionada(CondicionTaxativa condicionTaxativaSeleccionada) {
		this.condicionTaxativaSeleccionada = condicionTaxativaSeleccionada;
	}

	public List<CondicionTaxativa> getCondicionesTaxativasAAgregar() {
		return condicionesTaxativasAAgregar;
	}

	public void setCondicionesTaxativasAAgregar(List<CondicionTaxativa> condicionesTaxativasAAgregar) {
		this.condicionesTaxativasAAgregar = condicionesTaxativasAAgregar;
	}

	public CondicionTaxativa getCondicionTaxativaAAgregarSeleccionada() {
		return condicionTaxativaAAgregarSeleccionada;
	}

	public void setCondicionTaxativaAAgregarSeleccionada(CondicionTaxativa condicionTaxativaAAgregarSeleccionada) {
		this.condicionTaxativaAAgregarSeleccionada = condicionTaxativaAAgregarSeleccionada;
	}

	public List<CondicionComparativa> getListaCondicionesComparativas() {
		return listaCondicionesComparativas;
	}

	public void setListaCondicionesComparativas(List<CondicionComparativa> listaCondicionesComparativas) {
		this.listaCondicionesComparativas = listaCondicionesComparativas;
	}

	public CondicionComparativa getCondicionComparativaSeleccionada() {
		return condicionComparativaSeleccionada;
	}

	public void setCondicionComparativaSeleccionada(CondicionComparativa condicionComparativaSeleccionada) {
		this.condicionComparativaSeleccionada = condicionComparativaSeleccionada;
	}

	public List<CondicionComparativa> getCondicionesComparativasAAgregar() {
		return condicionesComparativasAAgregar;
	}

	public void setCondicionesComparativasAAgregar(List<CondicionComparativa> condicionesComparativasAAgregar) {
		this.condicionesComparativasAAgregar = condicionesComparativasAAgregar;
	}

	public CondicionComparativa getCondicionComparativaAAgregarSeleccionada() {
		return condicionComparativaAAgregarSeleccionada;
	}

	public void setCondicionComparativaAAgregarSeleccionada(CondicionComparativa condicionComparativaAAgregarSeleccionada) {
		this.condicionComparativaAAgregarSeleccionada = condicionComparativaAAgregarSeleccionada;
	}

	

	
}
