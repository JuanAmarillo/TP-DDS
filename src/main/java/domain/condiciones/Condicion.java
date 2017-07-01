package domain.condiciones;

import domain.Empresa;
import domain.indicadores.*;

public class Condicion {

	protected String nombre;
	public Empresa empresa;
	public Indicador indicador;
	public String periodo;
	// public String tipo?

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public boolean suNombreEs(String nombreCondicion) {
		return this.nombre.equals(nombreCondicion);
	}

}
