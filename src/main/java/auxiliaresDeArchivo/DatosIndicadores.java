package auxiliaresDeArchivo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.indicadores.BuilderIndicadorCustom;
import domain.indicadores.IndicadorCustom;

public class DatosIndicadores {
	List<BuilderIndicadorCustom> indicadores;
	
	public DatosIndicadores() {
		indicadores = new ArrayList<BuilderIndicadorCustom>();
	}
	
	public List<BuilderIndicadorCustom> getIndicadores() {
		return indicadores;
	}
	public void setIndicadores(List<BuilderIndicadorCustom> indicadores) {
		this.indicadores = indicadores;
	}
	
	public List<IndicadorCustom> buildIndicadores() {
		return indicadores.stream().map(ind -> ind.build()).collect(Collectors.toList());
	}
	
}
