package ui.vm.cargarMetodologiaVM;

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
import ui.vm.cargarMetodologiaVM.factories.AgregarMetodologia;
import ui.vm.cargarMetodologiaVM.factories.AgregarCondicionComparativa;
import ui.vm.cargarMetodologiaVM.factories.AgregarCondicionTaxativa;

@Observable
public class CargarMetodologiasVM {

	public String nombreMetodologia = "";
	public Double pesoDeComparativa = 0.0;

	public CondicionTaxativa condicionTaxativaSeleccionada = null;

	public CondicionComparativa condicionComparativaSeleccionada = null;

	public List<Condicion> condicionesAgregadas = new ArrayList<Condicion>();;
	public Condicion condicionAgregadaSeleccionada = null;

	public void cargarMetodologia() {
		new AgregarMetodologia().agregar(nombreMetodologia, condicionesAgregadas);
	}

	public void moverHaciaLaDerechaTaxativa() {
		new AgregarCondicionTaxativa(condicionTaxativaSeleccionada, condicionesAgregadas).agregar();
	}

	public void moverHaciaLaDerechaComparativa() {
		new AgregarCondicionComparativa(condicionComparativaSeleccionada, condicionesAgregadas, pesoDeComparativa)
				.agregar();
	}

	public void sacarCondicion() {
		if (noHayCondicionesParaSacar()) {
			condicionesAgregadas.remove(condicionAgregadaSeleccionada);
		}
	}

	public boolean noHayCondicionesParaSacar() {
		return condicionAgregadaSeleccionada != null;
	}

	// GETTERS Y SETTERS

	public String getNombreMetodologia() {
		return nombreMetodologia;
	}

	public void setNombreMetodologia(String nombreMetodologia) {
		this.nombreMetodologia = nombreMetodologia;
	}

	public List<CondicionTaxativa> getCondicionesTaxativas() {
		return RepositorioCondiciones.instance().getCondicionesTaxativas();
	}

	public CondicionTaxativa getCondicionTaxativaSeleccionada() {
		return condicionTaxativaSeleccionada;
	}

	public void setCondicionTaxativaSeleccionada(CondicionTaxativa condicionTaxativaSeleccionada) {
		this.condicionTaxativaSeleccionada = condicionTaxativaSeleccionada;
	}

	public List<Condicion> getCondicionesAgregadas() {
		return condicionesAgregadas;
	}

	public void setCondicionesAgregadas(List<Condicion> condicionesAgregadas) {
		this.condicionesAgregadas = condicionesAgregadas;
	}

	public Condicion getCondicionAgregadaSeleccionada() {
		return condicionAgregadaSeleccionada;
	}

	public void setCondicionAgregadaSeleccionada(Condicion condicionAgregadaSeleccionada) {
		this.condicionAgregadaSeleccionada = condicionAgregadaSeleccionada;
	}

	public List<CondicionComparativa> getCondicionesComparativas() {
		return RepositorioCondiciones.instance().getCondicionesComparativas();
	}

	public CondicionComparativa getCondicionComparativaSeleccionada() {
		return condicionComparativaSeleccionada;
	}

	public void setCondicionComparativaSeleccionada(CondicionComparativa condicionComparativaSeleccionada) {
		this.condicionComparativaSeleccionada = condicionComparativaSeleccionada;
	}

	public Double getPesoDeComparativa() {
		return pesoDeComparativa;
	}

	public void setPesoDeComparativa(Double pesoDeComparativa) {
		this.pesoDeComparativa = pesoDeComparativa;
	}

}
