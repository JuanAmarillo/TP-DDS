package domain.condiciones;

import java.util.Arrays;
import java.util.List;

import domain.indicadores.Indicador;
import scala.Char;

public abstract class Condicion {

	protected String nombre;
	public Indicador indicador;
	public String operador;

	public abstract boolean comparar();
	
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
