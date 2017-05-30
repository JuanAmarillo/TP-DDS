package ui.vm;

import java.io.IOException;

import org.uqbar.commons.utils.Observable;

import domain.Indicador;
import domain.repositorios.RepositorioIndicadores;
import externos.LevantaArchivoIndicadores;

@Observable
public class CargarIndicadorVM {
	public Indicador indicador;
	public String filePath;

	public CargarIndicadorVM() {
		super();
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilepath(String filepath) {
		this.filePath = filepath;
	}

	public void cargarArchivoIndicadores() throws IOException {
		new LevantaArchivoIndicadores().cargarArchivo(filePath);
	}

	public void cargarIndicador() throws IOException {
		RepositorioIndicadores.instance().agregarIndicador(indicador);
	}

}
