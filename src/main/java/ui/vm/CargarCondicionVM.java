package ui.vm;

import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import domain.condiciones.Condicion;
import domain.repositorios.RepositorioIndicadores;

@Observable
public class CargarCondicionVM {

	public String condicion;
	public List<String> condiciones;
	public String condicionSeleccionada;

	public List<String> indicadores;
	public String indicadorSeleccionado;

	public List<String> tipos;
	public String tipoSeleccionado;

	public List<String> operaciones = Arrays.asList(">", "<");
	public String operacionSeleccionada;

	public double valor;

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

	public List<String> getTipos() {
		return Condicion.getTipos();
	}

	public String getTipoSeleccionado() {
		return tipoSeleccionado;
	}

	public void setTipoSeleccionado(String tipoSeleccionado) {
		this.tipoSeleccionado = tipoSeleccionado;
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

	
}