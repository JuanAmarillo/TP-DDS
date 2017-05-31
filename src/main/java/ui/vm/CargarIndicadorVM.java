package ui.vm;

import java.io.IOException;

import org.eclipse.ui.dialogs.TwoPaneElementSelector;
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

	public void cargarIndicador()  {
		Indicador indicador = armarIndicador();
		validarIndicador(indicador);
		RepositorioIndicadores.instance().agregarIndicador(indicador);
	}
	
	public void validarIndicador(Indicador indicador){
		if(indicador.ecuacion.contains(indicador.nombreIndicador))
			throw new RuntimeException("El indicador no puede llamarse a si mismo");
		new AnalizadorDeIndicadores(null).scan(indicador).parser();
	}
	
	public Indicador armarIndicador(){
		String[] partesDelIndicador = indicador.split("=");
		Indicador indicador = new Indicador();
		indicador.setNombreIndicador(partesDelIndicador[0].trim());
		indicador.setEcuacion(partesDelIndicador[1]);
		return indicador;
	}

}
