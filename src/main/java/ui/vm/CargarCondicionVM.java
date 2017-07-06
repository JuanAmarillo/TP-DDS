package ui.vm;

import java.util.Arrays;
import java.util.List;

import org.uqbar.arena.windows.Dialog;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import domain.condiciones.BuilderCondicion;
import domain.condiciones.BuilderCondicionComparativa;
import domain.condiciones.BuilderCondicionTaxita;
import domain.condiciones.Condicion;
import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionTaxativa;
import domain.repositorios.RepositorioCondiciones;
import domain.repositorios.RepositorioIndicadores;
import ui.windows.CargarCondicionWindow;

@Observable
public class CargarCondicionVM {

	public String nombreCondicion = "";
	public String condicionSeleccionada = "";
	public String indicadorSeleccionado = "";
	public String operacionSeleccionada = "";

	public boolean taxativa = false;
	public boolean comparativa = false;
	public double  valor;
	
	private BuilderCondicion builderCondicion;
	
	
	public void cargarCondicion() {
		condicionSeleccionada();
		crearCondicion();
		avisarCambiosCondiciones();
		
	}

	private void crearCondicion() {
		Condicion condicion = buildearCondicion();
		RepositorioCondiciones.instance().agregarCondicion(condicion);
	}

	private Condicion buildearCondicion() {
		return builderCondicion.setNombre(nombreCondicion).setIndicador(indicadorSeleccionado)
			.setOperador(operacionSeleccionada).setValue(valor).build();
	}
	
	public void eliminarCondicion() {
		RepositorioCondiciones.instance().eliminarCondicion(condicionSeleccionada);
		avisarCambiosCondiciones();
	}
	
	private void condicionSeleccionada() {
		if(!taxativa && !comparativa )
			throw new RuntimeException("No se seleccionó el tipo de condición");
	}

	private void avisarCambiosCondiciones() {
		ObservableUtils.firePropertyChanged(this, "condiciones");
	}
	
	private void crearBuilderComparativa(){
		this.builderCondicion = new BuilderCondicionComparativa();
	}
	
	private void crearBuilderTaxito(){
		this.builderCondicion = new BuilderCondicionTaxita();
	}
	
	
	//GETTERS Y SETTERS
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

	public List<String> getOperaciones() {
		return Arrays.asList(">", "<");
	}

	public String getOperacionSeleccionada() {
		return operacionSeleccionada;
	}

	public void setOperacionSeleccionada(String operacionSeleccionada) {
		this.operacionSeleccionada = operacionSeleccionada;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public boolean getTaxativa() {
		return taxativa;
	}

	public void setTaxativa(boolean taxativa) {
		this.taxativa    = taxativa;
		this.comparativa = false;
		crearBuilderTaxito();
	}

	public boolean getComparativa() {
		return comparativa;
	}

	public void setComparativa(boolean comparativa) {
		this.comparativa = comparativa;
		this.taxativa    = false;
		crearBuilderComparativa();
		
	}
	
	
	
}