package ui.vm;

import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
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

	public String getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}

	public void setIndicadorSeleccionado(String indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}

	public java.util.List<String> getIndicadores() {
		return RepositorioIndicadores.instance().getNombresDeIndicadores();
	}

	public List<String> getTipos() {
		return Condicion.getTipos();
	}

	public String getTipoSeleccionado() {
		return tipoSeleccionado;
	}

	public void setTipoSeleccionado(String tipoSeleccionado) {
		this.tipoSeleccionado = tipoSeleccionado;
		avisarTipoCondicion();
	}

	private void avisarTipoCondicion() {
		ObservableUtils.firePropertyChanged(this, "tipoSeleccionado");
	}

}
