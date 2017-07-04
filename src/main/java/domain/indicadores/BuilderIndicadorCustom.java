package domain.indicadores;

import calculoIndicadores.ConstructoresIndicador.Analizador;

public class BuilderIndicadorCustom {
	private IndicadorCustom indicador;
	private String ecuacion;
	
	public BuilderIndicadorCustom(String indicador) {
		ecuacion = indicador;
	}
	
	public IndicadorCustom build() {
		return indicador;
	}
	
	public BuilderIndicadorCustom analizar() {
		new Analizador().scan(ecuacion).parser();
		return this;
	}
	
	public BuilderIndicadorCustom generarIndicador() {
		String[] partes =  ecuacion.split("=");
		indicador = new IndicadorCustom();
		setNombre(partes[0].trim());
		setEcuacion(partes[1].trim());
		return this;
	}

	private void setNombre(String nombre) {
		indicador.setNombre(nombre);
	}

	private void setEcuacion(String ecuacion) {
		indicador.setEcuacion(ecuacion);
	}
	
	public String getNombre() {
		return indicador.getNombre();
	}
}
