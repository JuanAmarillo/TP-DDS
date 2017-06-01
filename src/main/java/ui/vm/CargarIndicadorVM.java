package ui.vm;

import java.io.IOException;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import domain.Indicador;
import domain.repositorios.RepositorioIndicadores;
import externos.LevantaArchivoIndicadores;

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

	public void cargarIndicador() throws IOException{
		RepositorioIndicadores.instance().agregarIndicadorAPartirDe(indicador);
		new LevantaArchivoIndicadores().actualizarArchivoJson();
		ObservableUtils.firePropertyChanged(this, "indicadores");
	}

	public void eliminarIndicador() throws IOException{
		RepositorioIndicadores.instance().eliminarIndicadorAPartirDe(indicadorSeleccionado);
		new LevantaArchivoIndicadores().actualizarArchivoJson();
		ObservableUtils.firePropertyChanged(this, "indicadores");
	}

}
