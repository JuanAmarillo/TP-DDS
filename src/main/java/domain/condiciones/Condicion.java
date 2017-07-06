package domain.condiciones;

import domain.indicadores.Indicador;

public abstract class Condicion {

	protected String nombre;
	public    Indicador indicador;
	public    String operador;
	public    Boolean esCustom = true;
	
	public abstract Double primerValor(String periodo);
	public abstract Double segundoValor(String periodo);
	
	public int comparar(String periodo) {
		Double valor;
		if(esOperadorMayor()) {
			valor = primerValor(periodo) - segundoValor(periodo);
		}
		else
			valor = segundoValor(periodo) - primerValor(periodo);
		return valor.intValue();
	}
	
	public int comparar (Double primerValor, Double segundoValor) { 
		Double valor;
		if(esOperadorMayor()) {
			valor = primerValor - segundoValor;
		}
		else
			valor = segundoValor - primerValor;
		return valor.intValue();
	}
	
	public boolean esOperadorMayor() {
		return operador.equals(">");
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
