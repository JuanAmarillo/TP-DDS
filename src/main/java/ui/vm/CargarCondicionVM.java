package ui.vm;

import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import domain.condiciones.Condicion;
import domain.condiciones.OperadoresCondicion.*;
import domain.condiciones.buildersCondicion.BuilderCondicion;
import domain.condiciones.buildersCondicion.BuilderCondicionComparativa;
import domain.condiciones.buildersCondicion.BuilderCondicionTaxativa;
import domain.repositorios.RepositorioCondiciones;
import domain.repositorios.RepositorioIndicadores;

@Observable
public class CargarCondicionVM {

	private String nombreCondicion;
	private String condicionSeleccionada;
	private String indicadorSeleccionado;
	private OperadorCondicion operacionSeleccionada;
	private BuilderCondicion builderSeleccionado;
	private double valorCondicionTaxativa;

	public void cargarCondicion() {
		verSiSeleccionoCondicion();
		crearCondicion();
		avisarDeCambiosEnCondiciones();
	}

	private void crearCondicion() {
		Condicion condicion = buildearCondicion();
		RepositorioCondiciones.instance().agregar(condicion);
	}

	private Condicion buildearCondicion() {
		return builderSeleccionado.setNombre(nombreCondicion).setIndicador(indicadorSeleccionado)
				.setOperador(operacionSeleccionada).setValue(valorCondicionTaxativa).build();
	}

	public void eliminarCondicion() {
		RepositorioCondiciones.instance().eliminarCondicion(condicionSeleccionada);
		avisarDeCambiosEnCondiciones();
	}

	private void avisarDeCambiosEnCondiciones() {
		ObservableUtils.firePropertyChanged(this, "condiciones");
	}

	private void verSiSeleccionoCondicion() {
		if (noHayBuilderSeleccionado())
			throw new RuntimeException("No se seleccionó el tipo de condición");
	}

	private boolean noHayBuilderSeleccionado() {
		return builderSeleccionado == null;
	}

	// GETTERS Y SETTERS
	public String getNombreCondicion() {
		return nombreCondicion;
	}

	public void setNombreCondicion(String condicion) {
		this.nombreCondicion = condicion;
	}

	public List<String> getCondiciones() {
		return RepositorioCondiciones.instance().getNombres();
	}

	public String getCondicionSeleccionada() {
		return condicionSeleccionada;
	}

	public void setCondicionSeleccionada(String condicionSeleccionada) {
		this.condicionSeleccionada = condicionSeleccionada;
	}

	public List<String> getIndicadores() {
		return RepositorioIndicadores.instance().getNombres();
	}

	public String getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}

	public void setIndicadorSeleccionado(String indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}

	public List<OperadorCondicion> getOperaciones() {
		return Arrays.asList(new Mayor(), new Menor());
	}

	public OperadorCondicion getOperacionSeleccionada() {
		return operacionSeleccionada;
	}

	public void setOperacionSeleccionada(OperadorCondicion operacionSeleccionada) {
		this.operacionSeleccionada = operacionSeleccionada;
	}

	public List<BuilderCondicion> getBuilders() {
		return Arrays.asList(new BuilderCondicionTaxativa(), new BuilderCondicionComparativa());
	}

	public BuilderCondicion getBuilderSeleccionado() {
		return builderSeleccionado;
	}

	public void setbuilderSeleccionado(BuilderCondicion builderSeleccionado) {
		this.builderSeleccionado = builderSeleccionado;
		ObservableUtils.firePropertyChanged(this, "esTaxativa");
	}

	public double getValorCondicionTaxativa() {
		return valorCondicionTaxativa;
	}

	public void setValorCondicionTaxativa(double valor) {
		this.valorCondicionTaxativa = valor;
	}

	public boolean esTaxativa() {
		return builderSeleccionado.esTaxativa();
	}

}