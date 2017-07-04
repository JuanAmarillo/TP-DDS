package ui.vm;

import java.io.IOException;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import archivos.ActualizaIndicadores;
import domain.repositorios.RepositorioIndicadores;

@Observable
public class CargarIndicadorVM {

	public String indicador;
	public List<String> indicadores;
	public String indicadorSeleccionado;

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
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

	public void cargarIndicador() throws IOException {
		new ActualizaIndicadores().agregar(indicador);
		avisarCambioIndicadores();
	}

	public void eliminarIndicador() throws IOException {
		new ActualizaIndicadores().eliminar(indicadorSeleccionado);
		avisarCambioIndicadores();
	}

	private void avisarCambioIndicadores() {
		ObservableUtils.firePropertyChanged(this, "indicadores");
	}

}
