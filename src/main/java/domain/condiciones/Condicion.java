package domain.condiciones;

import java.util.List;

import domain.Empresa;
import domain.indicadores.Indicador;
import exceptions.NoSePuedeCalcularException;

public abstract class Condicion {

	protected String nombre;
	public    Indicador indicador;
	public    String operador;
	public    Boolean esCustom = true;
	
	public abstract List<Empresa> aplicarCondicion(List<Empresa> listaEmpresas, String string);
	
	public int comparar (Double primerValor, Double segundoValor) { 
		Double valor;
		if(esOperadorMayor()) 
			valor = primerValor - segundoValor;
		else
			valor = segundoValor - primerValor;
		return valor.intValue();
	}
	
	public boolean esOperadorMayor() {
		return operador.equals(">");
	}
	
	public void checkearCalculabilidad(Empresa empresa,String periodo) {
		if(!indicador.esCalculable(empresa, periodo)) 
			throw new NoSePuedeCalcularException(indicador.getNombre(), empresa.getNombre(), periodo);
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

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	
	
	
}
