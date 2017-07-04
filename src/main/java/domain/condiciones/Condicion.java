package domain.condiciones;

import java.util.Arrays;
import java.util.List;

import domain.indicadores.Indicador;

public class Condicion {

	protected String nombre;
	public Indicador indicador;
	public static List<String> tipos=Arrays.asList("Taxativa","Comparativa");

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	public boolean suNombreEs(String nombreCondicion) {
		return this.nombre.equals(nombreCondicion);
	}

	public static List<String> getTipos() {
		return tipos;
	}

	public void setTipos(List<String> tipos) {
		Condicion.tipos = tipos;
	}
	
	

}
