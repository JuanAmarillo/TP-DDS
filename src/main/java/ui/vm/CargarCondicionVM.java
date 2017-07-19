package ui.vm;

import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import domain.condiciones.BuilderCondicion;
import domain.condiciones.BuilderCondicionComparativa;
import domain.condiciones.BuilderCondicionTaxativa;
import domain.condiciones.Condicion;
import domain.condiciones.OperadoresCondicion.*;
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
		avisarCambiosEn("condiciones");
	}

	private void crearCondicion() {
		Condicion condicion = buildearCondicion();
		RepositorioCondiciones.instance().agregarCondicion(condicion);
	}

	private Condicion buildearCondicion() {
		return builderSeleccionado.setNombre(nombreCondicion).setIndicador(indicadorSeleccionado)
				.setOperador(operacionSeleccionada).setValue(valorCondicionTaxativa).build();
	}

	public void eliminarCondicion() {
		RepositorioCondiciones.instance().eliminarCondicion(condicionSeleccionada);
		avisarCambiosEn("condiciones");
	}

	private void verSiSeleccionoCondicion() {
		if (noHayBuilderSeleccionado())
			throw new RuntimeException("No se seleccionó el tipo de condición");
	}

	private boolean noHayBuilderSeleccionado() {
		return builderSeleccionado == null;
	}

	private void avisarCambiosEn(String property) {
		ObservableUtils.firePropertyChanged(this, property);
	}

	// GETTERS Y SETTERS
	public String getNombreCondicion() {
		return nombreCondicion;
	}

	public void setNombreCondicion(String condicion) {
		this.nombreCondicion = condicion;
	}

	public List<String> getCondiciones() {
		return RepositorioCondiciones.instance().getNombresDeCondiciones();
	}

	public String getCondicionSeleccionada() {
		return condicionSeleccionada;
	}

	public void setCondicionSeleccionada(String condicionSeleccionada) {
		this.condicionSeleccionada = condicionSeleccionada;
	}

	public List<String> getIndicadores() {
		return RepositorioIndicadores.instance().getNombresDeIndicadores();
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
		avisarCambiosEn("esTaxativa");
	}

	public double getValorCondicionTaxativa() {
		return valorCondicionTaxativa;
	}

	public void setValorCondicionTaxativa(double valor) {
		this.valorCondicionTaxativa = valor;
	}

	public boolean esTaxativa() {
		return builderSeleccionado instanceof BuilderCondicionTaxativa;
	}

}