package domain.condiciones;

import java.util.List;

import domain.Empresa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;
import exceptions.NoSePuedeCalcularException;

public abstract class Condicion {

	protected String nombre;
	public    Indicador indicador;
	public    OperadorCondicion operador;
	public    Boolean esCustom = true;
	
	public abstract List<Empresa> aplicarCondicion(List<Empresa> listaEmpresas, String string);
	
	public int comparar (Double valorIndicadorUno, Double valorIndicadorDos) { 
		return operador.comparar(valorIndicadorUno, valorIndicadorDos);
	}
	
	public boolean esCustom(){ 
		return esCustom;
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

	public OperadorCondicion getOperador() {
		return operador;
	}

	public void setOperador(OperadorCondicion operador) {
		this.operador = operador;
	}

	
	
	abstract public boolean esTaxativa();
	
	abstract public boolean esComparativa();
	
	
}
