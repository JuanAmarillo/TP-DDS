package ui.vm;

import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

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

	public void cargarIndicador() {
		RepositorioIndicadores.instance().agregarIndicadorAPartirDe(indicador);
		ObservableUtils.firePropertyChanged(this, "indicadores");
	}

	public void eliminarIndicador() {
		RepositorioIndicadores.instance().eliminarIndicadorAPartirDe(indicadorSeleccionado);
		ObservableUtils.firePropertyChanged(this, "indicadores");
	}

}
