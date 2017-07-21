package ui.vm;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import domain.condiciones.Condicion;
import domain.metodologias.AplicadorDeCondiciones;
import domain.metodologias.ListaMetodologia;
import domain.metodologias.Metodologia;
import domain.repositorios.RepositorioCondiciones;
import domain.repositorios.RepositorioMetodologias;

@Observable
public class CargarMetodologiasVM {

	public String nombreMetodologia;

	public List<String> listaCondicionesTaxativas;
	public String condicionTaxativaSeleccionada;
	
	public List<String> condicionesTaxativasAAgregar;
	public String condicionTaxativaAAgregarSeleccionada;
	
	public List<String> listaCondicionesComparativas;
	public String condicionComparativaSeleccionada;
	
	public List<String> condicionesComparativasAAgregar;
	public String condicionComparativaAAgregarSeleccionada;
	
	public CargarMetodologiasVM() {
		listaCondicionesTaxativas = RepositorioCondiciones.instance().getNombresDeCondicionesTaxativas();
		listaCondicionesComparativas = RepositorioCondiciones.instance().getNombresDeCondicionesComparativas();
		condicionesTaxativasAAgregar = new ArrayList<String>();
		condicionesComparativasAAgregar = new ArrayList<String>();
		inicializarStrings();
	}
	
	private void inicializarStrings() {
		condicionTaxativaSeleccionada = "";
		condicionTaxativaAAgregarSeleccionada = "";
		condicionComparativaSeleccionada = "";
		condicionComparativaAAgregarSeleccionada = "";
		nombreMetodologia = "";
	}

	public void cargarMetodologia() {
		realizarValidaciones();
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
		String valor = condicionTaxativaAAgregarSeleccionada;
		if(valor != null) {
			condicionesTaxativasAAgregar.remove(valor);
			listaCondicionesTaxativas.add(valor);
			actualizarListas("listaCondicionesTaxativas", "condicionesTaxativasAAgregar");
		}
	}	

	public void moverHaciaLaDerechaTaxativa() {
		String valor = condicionTaxativaSeleccionada;
		if(valor != null) {
			condicionesTaxativasAAgregar.add(valor);
			listaCondicionesTaxativas.remove(valor);
			actualizarListas("listaCondicionesTaxativas", "condicionesTaxativasAAgregar");
		}
	}
	
	public void moverHaciaLaDerechaComparativa() {
		String valor = condicionComparativaSeleccionada;
		if(valor != null) {
			condicionesComparativasAAgregar.add(valor);
			listaCondicionesComparativas.remove(valor);
			actualizarListas("condicionesComparativasAAgregar", "listaCondicionesComparativas");
		}
	}

	public void moverHaciaLaIzquierdaComparativa() {
		String valor = condicionComparativaAAgregarSeleccionada;
		if(valor != null) {
			condicionesComparativasAAgregar.remove(valor);
			listaCondicionesComparativas.add(valor);
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

	public List<String> getListaCondicionesTaxativas() {
		return listaCondicionesTaxativas;
	}

	public void setListaCondicionesTaxativas(List<String> listaCondicionesTaxativas) {
		this.listaCondicionesTaxativas = listaCondicionesTaxativas;
	}

	public String getCondicionTaxativaSeleccionada() {
		return condicionTaxativaSeleccionada;
	}

	public void setCondicionTaxativaSeleccionada(String condicionSeleccionada) {
		this.condicionTaxativaSeleccionada = condicionSeleccionada;
	}

	public List<String> getCondicionesTaxativasAAgregar() {
		return condicionesTaxativasAAgregar;
	}

	public void setCondicionesTaxativasAAgregar(List<String> condicionesAAgregar) {
		this.condicionesTaxativasAAgregar = condicionesAAgregar;
	}

	public String getCondicionTaxativaAAgregarSeleccionada() {
		return condicionTaxativaAAgregarSeleccionada;
	}

	public void setCondicionTaxativaAAgregarSeleccionada(String condicionAAgregarSeleccionada) {
		this.condicionTaxativaAAgregarSeleccionada = condicionAAgregarSeleccionada;
	}

	public List<String> getListaCondicionesComparativas() {
		return listaCondicionesComparativas;
	}

	public void setListaCondicionesComparativas(List<String> listaCondicionesComparativas) {
		this.listaCondicionesComparativas = listaCondicionesComparativas;
	}

	public String getCondicionComparativaSeleccionada() {
		return condicionComparativaSeleccionada;
	}

	public void setCondicionComparativaSeleccionada(String condicionComparativaSeleccionada) {
		this.condicionComparativaSeleccionada = condicionComparativaSeleccionada;
	}

	public List<String> getCondicionesComparativasAAgregar() {
		return condicionesComparativasAAgregar;
	}

	public void setCondicionesComparativasAAgregar(List<String> condicionesComparativasAAgregar) {
		this.condicionesComparativasAAgregar = condicionesComparativasAAgregar;
	}

	public String getCondicionComparativaAAgregarSeleccionada() {
		return condicionComparativaAAgregarSeleccionada;
	}

	public void setCondicionComparativaAAgregarSeleccionada(String condicionComparativaAAgregarSeleccionada) {
		this.condicionComparativaAAgregarSeleccionada = condicionComparativaAAgregarSeleccionada;
	}

	

	
}
