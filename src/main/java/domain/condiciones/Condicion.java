package domain.condiciones;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;
import domain.metodologias.EmpresaConPeso;

@Observable
public abstract class Condicion implements CondicionCustom {

	protected String nombre;
	protected Indicador indicador;
	protected OperadorCondicion operador;
	protected String nombreYPeso;

	public Condicion(String nombre, Indicador indicador, OperadorCondicion operador) {
		this.nombre = nombre;
		this.indicador = indicador;
		this.operador = operador;
	}

	public abstract List<EmpresaConPeso> aplicarCondicion(List<EmpresaConPeso> empresasConPeso);

	public abstract List<EmpresaConPeso> aplicarCondicionEnPeriodo(List<EmpresaConPeso> empresasConPeso, String periodo);
		
	public abstract Boolean esTaxativa();

	public abstract String getNombreYPeso();
	
	// GETTERS Y SETTERS

	public Condicion(String nombre) {
		this.nombre = nombre;
	}

	public Integer comparar(Double valorIndicadorUno, Double valorIndicadorDos) {
		return operador.comparar(valorIndicadorUno, valorIndicadorDos);
	}

	public Double calcularIndicador(Empresa empresa, String periodo) {
		return indicador.calcularIndicador(empresa, periodo);
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
