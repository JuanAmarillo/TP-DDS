package ui.vm;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import domain.condiciones.Condicion;
import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionTaxativa;
import domain.condiciones.ManejadorDePesos;
import domain.metodologias.Metodologia;
import domain.repositorios.RepositorioCondiciones;
import domain.repositorios.RepositorioMetodologias;

@Observable
public class CargarMetodologiasVM {

	public String nombreMetodologia = "";
	public Double pesoDeComparativa = 0.0;

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
		// para que funcione por ahora
		List<Condicion> condicionesAgregar = new ArrayList<>();
		condicionesAgregar.addAll(condicionesTaxativasAAgregar);
		condicionesAgregar.addAll(condicionesComparativasAAgregar);
		RepositorioMetodologias.instance().agregarMetodologia(new Metodologia(nombreMetodologia, condicionesAgregar));
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
		if (condicionesTaxativasAAgregar.size() == 0 && condicionesComparativasAAgregar.size() == 0)
			throw new RuntimeException("No se seleccionó ninguna condición");
	}

	public void moverHaciaLaIzquierdaTaxativa() {
		if (condicionTaxativaAAgregarSeleccionada != null) {
			listaCondicionesTaxativas.add(condicionTaxativaAAgregarSeleccionada);
			condicionesTaxativasAAgregar.remove(condicionTaxativaAAgregarSeleccionada);
			condicionTaxativaAAgregarSeleccionada = null;
			avisarCambiosEnTaxativa();
		}
	}

	public void moverHaciaLaDerechaTaxativa() {
		if (condicionTaxativaSeleccionada != null) {
			condicionesTaxativasAAgregar.add(condicionTaxativaSeleccionada);
			listaCondicionesTaxativas.remove(condicionTaxativaSeleccionada);
			// agregarPeso(condicionTaxativaSeleccionada);
			// crearTupla();
			condicionTaxativaSeleccionada = null;
			avisarCambiosEnTaxativa();
		}
	}

	public void avisarCambiosEnTaxativa() {
		VmUtils.avisarCambios(this, "listaCondicionesTaxativas", "condicionesTaxativasAAgregar");
	}

	public void moverHaciaLaDerechaComparativa() {
		if (condicionComparativaSeleccionada != null) {
			validarPeso();
			ManejadorDePesos manejadorDePesos = new ManejadorDePesos(pesoDeComparativa);
			condicionesComparativasAAgregar.add(condicionComparativaSeleccionada.setManejadorDePesos(manejadorDePesos));
			listaCondicionesComparativas.remove(condicionComparativaSeleccionada);
			condicionComparativaSeleccionada = null;
			avisarCambiosEnComparativa();
			resetPeso();
		}
	}

	private void resetPeso() {
		pesoDeComparativa = 0.0;
	}

	private void validarPeso() {
		if (pesoDeComparativa <= 0)
			throw new RuntimeException("Debe agregar un peso para la condicion");
	}

	public void moverHaciaLaIzquierdaComparativa() {
		if (condicionComparativaAAgregarSeleccionada != null) {
			listaCondicionesComparativas.add(condicionComparativaAAgregarSeleccionada);
			condicionesComparativasAAgregar.remove(condicionComparativaAAgregarSeleccionada);
			condicionComparativaAAgregarSeleccionada = null;
			avisarCambiosEnComparativa();
		}
	}

	public void avisarCambiosEnComparativa() {
		VmUtils.avisarCambios(this, "condicionesComparativasAAgregar", "listaCondicionesComparativas");
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

	public void setCondicionComparativaAAgregarSeleccionada(
			CondicionComparativa condicionComparativaAAgregarSeleccionada) {
		this.condicionComparativaAAgregarSeleccionada = condicionComparativaAAgregarSeleccionada;
	}

	public Double getPesoDeComparativa() {
		return pesoDeComparativa;
	}

	public void setPesoDeComparativa(Double pesoDeComparativa) {
		this.pesoDeComparativa = pesoDeComparativa;
	}

}
