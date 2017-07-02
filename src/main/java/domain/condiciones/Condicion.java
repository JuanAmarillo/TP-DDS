package domain.condiciones;

import domain.indicadores.*;

public class Condicion {

	protected String nombre;
	public Indicador indicador;
	// public String tipo?

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

}
