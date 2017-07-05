package ui.vm;

import java.util.Arrays;
import java.util.List;

import org.uqbar.arena.windows.Dialog;
import org.uqbar.commons.utils.Observable;

import domain.condiciones.Condicion;
import domain.repositorios.RepositorioIndicadores;
import ui.windows.CargarCondicionWindow;

@Observable
public class CargarCondicionVM {

	public String condicion;
	public List<String> condiciones;
	public String condicionSeleccionada;

	public List<String> indicadores;
	public String indicadorSeleccionado;

	public boolean taxativa = false;
	public boolean comparativa = false;

	public List<String> tipoDeCondicion = Arrays.asList("Comparativa","Taxativa");
	public String tipoSeleccionado;
	
	public List<String> operaciones = Arrays.asList(">", "<");
	public String operacionSeleccionada;

	public double valor;

	public void cargarCondicion() {
		
	}
	
	//GETTERS Y SETTERS
	
	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public List<String> getCondiciones() {
		return condiciones;
	}

	public String getCondicionSeleccionada() {
		return condicionSeleccionada;
	}

	public void setCondicionSeleccionada(String condicionSeleccionada) {
		this.condicionSeleccionada = condicionSeleccionada;
	}

	public java.util.List<String> getIndicadores() {
		return RepositorioIndicadores.instance().getNombresDeIndicadores();
	}

	public String getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}

	public void setIndicadorSeleccionado(String indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}

	public List<String> getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(List<String> operaciones) {
		this.operaciones = operaciones;
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
		this.taxativa = taxativa;
		comparativa = false;
	}

	public boolean getComparativa() {
		return comparativa;
	}

	public void setComparativa(boolean comparativa) {
		this.comparativa = comparativa;
		taxativa = false;
	}
	
	
	
}