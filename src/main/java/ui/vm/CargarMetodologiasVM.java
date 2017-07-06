package ui.vm;



import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import domain.repositorios.RepositorioCondiciones;

@Observable
public class CargarMetodologiasVM {

	public String nombreMetodologia;
	
	public List<String> condicionesTaxativas;
	public String taxativaSeleccionada;
	
	public List<String> condicionesComparativas;
	public String comparativaSeleccionada;
	
	public String taxativaMetodologia;
	public List<String> taxativasMetodologia;

	public String comparativaMetodologia;
	public List<String> comparativasMetodologia;
	
	public CargarMetodologiasVM() {
		condicionesTaxativas = RepositorioCondiciones.instance().getNombresDeCondiciones();
		condicionesComparativas = RepositorioCondiciones.instance().getNombresDeCondiciones();
		taxativasMetodologia = new ArrayList<String>();
		comparativasMetodologia = new ArrayList<String>();
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
	public String getTaxativaMetodologia() {
		return taxativaMetodologia;
	}
	public void setTaxativaMetodologia(String taxativa) {
		this.taxativaMetodologia = taxativa;
	}
	public List<String> getTaxativasMetodologia() {
		return taxativasMetodologia;
	}
	public void setTaxativasMetodologia(List<String> taxativasMetodologia) {
		this.taxativasMetodologia = taxativasMetodologia;
	}
	public String getComparativaMetodologia() {
		return comparativaMetodologia;
	}
	public void setComparativaMetodologia(String comparativa) {
		this.comparativaMetodologia = comparativa;
	}
	public List<String> getComparativasMetodologia() {
		return comparativasMetodologia;
	}
	public void setComparativasMetodologia(List<String> comparativasMetodologia) {
		this.comparativasMetodologia = comparativasMetodologia;
	}



}
