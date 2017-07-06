package ui.vm;



import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import domain.repositorios.RepositorioCondiciones;

@Observable
public class CargarMetodologiasVM {

	public String nombreMetodologia;
	
	public List<String> condicionesTaxativas;
	public String taxativaSeleccionada;
	
	public List<String> condicionesComparativas;
	public String comparativaSeleccionada ;
	
	public String condicionTaxativaAAgregarSeleccionada ;
	public List<String> condicionesTaxativasAAgregar;

	public String condicionComparativaAAgregarSeleccionada;
	public List<String> condicionesComparativasAAgregar;
	
	public CargarMetodologiasVM() {
		condicionesTaxativas = RepositorioCondiciones.instance().getNombresDeCondicionesTaxativas();
		condicionesComparativas = RepositorioCondiciones.instance().getNombresDeCondicionesComparativas();
		condicionesTaxativasAAgregar = new ArrayList<String>();
		condicionesComparativasAAgregar = new ArrayList<String>();
		inicializarStrings();
	}
	
	private void inicializarStrings() {
		taxativaSeleccionada = condicionesTaxativas.stream().findFirst().get();
		comparativaSeleccionada = condicionesComparativas.stream().findFirst().get();
		
	}

	public void moverHaciaLaDerechaTaxativa() {
		String valor = taxativaSeleccionada;
		if(taxativaSeleccionada != null) {
			condicionesTaxativasAAgregar.add(valor);
			condicionesTaxativas.remove(valor);
			actualizarListasTaxativas();
		}
	}	

	public void moverHaciaLaIzquierdaTaxativa() {
		String valor = condicionTaxativaAAgregarSeleccionada;
		if(condicionTaxativaAAgregarSeleccionada != null) {
			condicionesTaxativas.add(valor);
			condicionesTaxativasAAgregar.remove(valor);
			actualizarListasTaxativas();
		}
	}
	
	private void actualizarListasTaxativas() {
		ObservableUtils.firePropertyChanged(this, "condicionesTaxativasAAgregar");
		ObservableUtils.firePropertyChanged(this, "condicionesTaxativas");
		
	}

	private void actualizarListasComparativas() {
		ObservableUtils.firePropertyChanged(this, "condicionesComparativasAAgregar");
		ObservableUtils.firePropertyChanged(this, "condicionesComparativas");
		
	}
	
	public void moverHaciaLaIzquierdaComparativa() {
		String valor = condicionComparativaAAgregarSeleccionada;
		if(valor != null) {
			condicionesComparativasAAgregar.remove(valor);
			condicionesComparativas.add(valor);
			actualizarListasComparativas();
		}
	}

	public void moverHaciaLaDerechaComparativa() {
		String valor = comparativaSeleccionada;
		if(valor != null) {
			condicionesComparativasAAgregar.add(valor);
			condicionesComparativas.remove(valor);
			actualizarListasComparativas();
			System.out.println(condicionesComparativasAAgregar.size());
		}
	}
	
	public String getNombreMetodologia() {
		return nombreMetodologia;
	}
	public void setNombreMetodologia(String nombreMetodologia) {
		this.nombreMetodologia = nombreMetodologia;
	}
	public List<String> getCondicionesTaxativas() {
		return condicionesTaxativas;
	}
	public void setCondicionesTaxativas(List<String> taxativas) {
		this.condicionesTaxativas = taxativas;
	}
	public String getTaxativaSeleccionada() {
		return taxativaSeleccionada;
	}
	public void setTaxativaSeleccionada(String taxSeleccionada) {
		this.taxativaSeleccionada = taxSeleccionada;
	}
	public List<String> getCondicionesComparativas() {
		return condicionesComparativas;
	}
	public void setCondicionesComparativas(List<String> comparativas) {
		this.condicionesComparativas = comparativas;
	}
	public String getComparativaSeleccionada() {
		return comparativaSeleccionada;
	}
	public void setComparativaSeleccionada(String comparativa) {
		this.comparativaSeleccionada = comparativa;
	}
	public String getCondicionTaxativaAAgregarSeleccionada() {
		return condicionTaxativaAAgregarSeleccionada;
	}
	public void setCondicionTaxativaAAgregarSeleccionada(String taxativa) {
		this.condicionTaxativaAAgregarSeleccionada = taxativa;
	}
	public List<String> getCondicionesTaxativasAAgregar() {
		return condicionesTaxativasAAgregar;
	}
	public void setCondicionesTaxativasAAgregar(List<String> taxativasMetodologia) {
		this.condicionesTaxativasAAgregar = taxativasMetodologia;
	}
	public String getCondicionComparativaAAgregarSeleccionada() {
		return condicionComparativaAAgregarSeleccionada;
	}
	public void setCondicionComparativaAAgregarSeleccionada(String comparativa) {
		this.condicionComparativaAAgregarSeleccionada = comparativa;
	}
	public List<String> getCondicionesComparativasAAgregar() {
		return condicionesComparativasAAgregar;
	}
	public void setCondicionesComparativasAAgregar(List<String> comparativasMetodologia) {
		this.condicionesComparativasAAgregar = comparativasMetodologia;
	}

	public void cargarMetodologia() {
		validarQueHayaAlgunaCondicion();
		
	}

	private void validarQueHayaAlgunaCondicion() {
		if(condicionesComparativasAAgregar.size() == 0 && condicionesTaxativasAAgregar.size() == 0)
			throw new RuntimeException("No se seleccionó ninguna condición");
	}





}
