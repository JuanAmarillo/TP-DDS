package ui.vm;

import java.io.IOException;

import org.uqbar.commons.utils.Observable;

import domain.Indicador;
import domain.repositorios.RepositorioIndicadores;
import externos.AnalizadorDeIndicadores;
import externos.LevantaArchivoIndicadores;

@Observable
public class CargarIndicadorVM {
	public String indicador;
	public String filePath;

	public CargarIndicadorVM() {
		super();
	}

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
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
		try{
			Indicador indicador = armarIndicador();
			new AnalizadorDeIndicadores(null).scan(indicador).parser();
			RepositorioIndicadores.instance().agregarIndicador(indicador);
		}catch(RuntimeException e){
			//Mandar mensaje de errorS
		}
	}
	
	public Indicador armarIndicador(){
		String[] partesDelIndicador = indicador.split("=");
		Indicador indicador = new Indicador();
		indicador.nombreIndicador = partesDelIndicador[0].trim();
		indicador.ecuacion = partesDelIndicador[1];
		return indicador;
	}

}
