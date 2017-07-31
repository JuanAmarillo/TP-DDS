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

	public CondicionTaxativa condicionTaxativaSeleccionada = null;
	
	public CondicionComparativa condicionComparativaSeleccionada = null;

	public List<Condicion> condicionesAgregadas;
	public Condicion condicionAgregadaSeleccionada = null;

	public CargarMetodologiasVM() {
		condicionesAgregadas = new ArrayList<Condicion>();
	}

	public void cargarMetodologia() {
		realizarValidaciones();
		RepositorioMetodologias.instance().agregarMetodologia(new Metodologia(nombreMetodologia, condicionesAgregadas));
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
		if (condicionesAgregadas.size() == 0)
			throw new RuntimeException("No se seleccionó ninguna condición");
	}

	public void moverHaciaLaIzquierdaTaxativa() {
		if (condicionAgregadaSeleccionada != null) {
			condicionesAgregadas.remove(condicionAgregadaSeleccionada);
		}
	}

	public void moverHaciaLaDerechaTaxativa() {
		if (condicionTaxativaSeleccionada != null && noSeAgregoTaxativa()) {
			condicionesAgregadas.add(condicionTaxativaSeleccionada);
		}
	}

	private boolean noSeAgregoTaxativa() {
		return !condicionesAgregadas.contains(condicionTaxativaSeleccionada);
	}

	private boolean noSeAgregoComparativa() {
		return !condicionesAgregadas.contains(condicionComparativaSeleccionada);
	}

	public void moverHaciaLaDerechaComparativa() {
		if (condicionComparativaSeleccionada != null && noSeAgregoComparativa()) {
			validarPeso();
			ManejadorDePesos manejadorDePesos = new ManejadorDePesos(pesoDeComparativa);
			condicionesAgregadas.add(condicionComparativaSeleccionada.setManejadorDePesos(manejadorDePesos));
		}
	}

	private void validarPeso() {
		if (pesoDeComparativa <= 0)
			throw new RuntimeException("Debe agregar un peso para la condicion");
	}

	public void moverHaciaLaIzquierdaComparativa() {
		if (condicionAgregadaSeleccionada != null) {
			condicionesAgregadas.remove(condicionAgregadaSeleccionada);
		}
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
