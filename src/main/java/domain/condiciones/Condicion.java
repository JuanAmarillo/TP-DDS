package domain.condiciones;

import java.util.List;

import domain.Empresa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;
import exceptions.NoSePuedeCalcularException;

public abstract class Condicion implements CondicionCustom{

	protected String nombre;
	protected Indicador indicador;
	protected OperadorCondicion operador;
	
	public abstract List<Empresa> aplicarCondicion(List<Empresa> listaEmpresas, String string);
	public abstract Boolean esTaxativa();
	
	public int comparar (Double valorIndicadorUno, Double valorIndicadorDos) { 
		return operador.comparar(valorIndicadorUno, valorIndicadorDos);
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
	
	
}
