package ui.vm;

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

	public void cargarIndicador()  {
		Indicador indicador = armarIndicador();
		validarIndicador(indicador);
		RepositorioIndicadores.instance().agregarIndicador(indicador);
		ObservableUtils.firePropertyChanged(this, "indicadores");
	}
	
	private void validarIndicador(Indicador indicador){
		ecuacionContieneAlNombre(indicador);
		indicadorExistente(indicador);
		new AnalizadorDeIndicadores(null).scan(indicador).parser();
	}
	
	private void indicadorExistente(Indicador indicador) {
		if(RepositorioIndicadores.instance().contieneElIndicador(indicador.nombreIndicador))
			throw new RuntimeException("El indicador ya existe");
	}

	private void ecuacionContieneAlNombre(Indicador indicador){
		if(indicador.ecuacion.contains(indicador.nombreIndicador))
			throw new RuntimeException("El indicador no puede llamarse a si mismo");
	}
	
	public Indicador armarIndicador(){
		String[] partesDelIndicador = indicador.split("=");
		Indicador indicador = new Indicador();
		indicador.setNombreIndicador(partesDelIndicador[0].trim());
		indicador.setEcuacion(partesDelIndicador[1]);
		return indicador;
	}

}
