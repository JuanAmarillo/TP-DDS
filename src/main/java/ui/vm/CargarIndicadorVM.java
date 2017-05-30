package ui.vm;

import java.io.IOException;

import org.uqbar.commons.utils.Observable;

import externos.LevantaArchivoIndicadores;

@Observable
public class CargarIndicadorVM {
	public String indicador;
	public String filepath;
	
	public CargarIndicadorVM(){
		super();
	}

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public void cargarIndicador() throws IOException {
		new LevantaArchivoIndicadores().cargarArchivo(filepath);
	}

}
