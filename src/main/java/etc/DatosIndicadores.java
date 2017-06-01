package etc;

import java.util.ArrayList;
import java.util.List;

import domain.Indicador;

public class DatosIndicadores {
	List<Indicador> indicadores;
	
	public DatosIndicadores() {
		indicadores = new ArrayList<Indicador>();
	}
	
	public List<Indicador> getIndicadores() {
		return indicadores;
	}
	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}
	
}
