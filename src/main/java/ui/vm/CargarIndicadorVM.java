package ui.vm;

import java.io.IOException;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import domain.repositorios.RepositorioIndicadores;

@Observable
public class CargarIndicadorVM {

	public String indicador;
	public String indicadorSeleccionado;
	public String filePath;

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
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void cargarIndicador() throws IOException {
		RepositorioIndicadores.instance().agregarIndicadorAPartirDel(indicador);
		avisarCambioIndicadores();
	}

	public void eliminarIndicador() throws IOException {		
		RepositorioIndicadores.instance().eliminarIndicadorAPartirDel(indicadorSeleccionado);
		avisarCambioIndicadores();
	}
	
	private void avisarCambioIndicadores() {
		ObservableUtils.firePropertyChanged(this, "indicadores");
	}

}
