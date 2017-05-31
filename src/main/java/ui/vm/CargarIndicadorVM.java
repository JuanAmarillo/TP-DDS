package ui.vm;

import java.io.IOException;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import domain.Indicador;
import domain.repositorios.RepositorioIndicadores;
import externos.AnalizadorDeIndicadores;
import externos.LevantaArchivoIndicadores;

@Observable
public class CargarIndicadorVM {
	public String indicador;
	public String filePath;

	public List<String> indicadores;
	public String indicadorSeleccionado;

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
	
	public List<String> getIndicadores() {
		return RepositorioIndicadores.instance().getNombresDeIndicadores();
	}
	
	public String getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}
	
	public void setIndicadorSeleccionado(String indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}

	public void cargarArchivoIndicadores() throws IOException {
		new LevantaArchivoIndicadores().cargarArchivo(filePath);
	}

	public void cargarIndicador()  {
		Indicador indicador = armarIndicador();
		validarIndicador(indicador);
		RepositorioIndicadores.instance().agregarIndicador(indicador);
		ObservableUtils.firePropertyChanged(this, "indicadores");
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
