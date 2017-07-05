package domain.condiciones;

import java.util.Arrays;
import java.util.List;

import domain.indicadores.Indicador;
import scala.Char;

public abstract class Condicion {

	protected String nombre;
	public Indicador indicador;
	public String operador;
	public boolean esCustom = true;
	
	public abstract boolean comparar();
	
	public boolean esOperadorMayor() {
		return operador.equals(">");
	}
	
	public boolean esCustom(){ 
		return esCustom;
	}
	
	public void setOrigen(boolean valor) {
		esCustom = valor;
	}
	
	public boolean suNombreEs(String nombreCondicion) {
		return nombre.equals(nombreCondicion);
	}
	
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

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}
	
	
}
