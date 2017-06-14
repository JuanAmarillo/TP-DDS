package etc;

import java.util.ArrayList;
import java.util.List;

import domain.indicadores.IndicadorCustom;

public class DatosIndicadores {
	List<IndicadorCustom> indicadores;
	
	public DatosIndicadores() {
		indicadores = new ArrayList<IndicadorCustom>();
	}
	
	public List<IndicadorCustom> getIndicadores() {
		return indicadores;
	}
	public void setIndicadores(List<IndicadorCustom> indicadores) {
		this.indicadores = indicadores;
	}
	
}
