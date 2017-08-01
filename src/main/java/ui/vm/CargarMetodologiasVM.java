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
		agregarMetodologia();
	}

	public void agregarMetodologia() {
		Metodologia nuevaMetodologia = new Metodologia(nombreMetodologia, condicionesAgregadas);
		RepositorioMetodologias.instance().agregarMetodologia(nuevaMetodologia);
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
		if (noHayCondicionesAgregadas())
			throw new RuntimeException("No se seleccionó ninguna condición");
	}

	public boolean noHayCondicionesAgregadas() {
		return condicionesAgregadas.size() == 0;
	}

	// factoryMoverIzquierdaTaxativa
	public void moverHaciaLaIzquierdaTaxativa() {
		if (condicionAgregadaSeleccionada != null) {
			condicionesAgregadas.remove(condicionAgregadaSeleccionada);
		}
	}
	//

	// factoryMoverDerechaTaxativa
	public void moverHaciaLaDerechaTaxativa() {
		validacionesTaxativa();
		condicionesAgregadas.add(condicionTaxativaSeleccionada);
	}

	private void validacionesTaxativa() {
		if (condicionTaxativaSeleccionada == null)
			throw new RuntimeException("Seleccione una condicion taxativa");
		if (seAgregoTaxativa())
			throw new RuntimeException("Esa condicion ya fue agregada");
	}

	private boolean seAgregoTaxativa() {
		return condicionesAgregadas.contains(condicionTaxativaSeleccionada);
	}
	//

	// factoryMoverDerechaComparativa
	public void moverHaciaLaDerechaComparativa() {
		validacionesComparativa();
		ManejadorDePesos manejadorDePesos = new ManejadorDePesos(pesoDeComparativa);
		condicionesAgregadas.add(condicionComparativaSeleccionada.setManejadorDePesos(manejadorDePesos));
	}

	private void validacionesComparativa() {
		if (condicionComparativaSeleccionada == null)
			throw new RuntimeException("Seleccione una condicion comparativa");
		if (seAgregoComparativa())
			throw new RuntimeException("Esa condicion ya fue agregada");
		if (pesoDeComparativa <= 0)
			throw new RuntimeException("Debe agregar un peso para la condicion");
	}

	private boolean seAgregoComparativa() {
		return !condicionesAgregadas.contains(condicionComparativaSeleccionada);
	}
	//

	// factoryMoverIzquierdaComparativa
	public void moverHaciaLaIzquierdaComparativa() {
		if (condicionAgregadaSeleccionada != null) {
			condicionesAgregadas.remove(condicionAgregadaSeleccionada);
		}
	}
	//

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
