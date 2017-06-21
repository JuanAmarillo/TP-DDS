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

	public void cargarIndicador() throws IOException,RuntimeException{
		RepositorioIndicadores.instance().agregarIndicadorAPartirDe(indicador);
		actualizaIndicadores();
	}

	public void eliminarIndicador() throws IOException{
		RepositorioIndicadores.instance().eliminarIndicadorAPartirDe(indicadorSeleccionado);
		actualizaIndicadores();
	}
	
	private void actualizaIndicadores() throws IOException {
		ActualizaIndicadores.actualizarArchivoJson();
		ObservableUtils.firePropertyChanged(this, "indicadores");
	}


}
