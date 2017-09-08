package domain.condiciones;

import java.util.List;

import org.javatuples.Pair;
import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;

@Observable
public abstract class Condicion implements CondicionCustom {

	protected String nombre;
	protected Indicador indicador;
	protected OperadorCondicion operador;

	public Condicion(String nombre, Indicador indicador, OperadorCondicion operador) {
		this.nombre = nombre;
		this.indicador = indicador;
		this.operador = operador;
	}

	public abstract List<Empresa> aplicarCondicionEnPeriodo(List<Empresa> empresas, String periodo);

	public abstract List<Empresa> aplicarCondicion(List<Empresa> empresas);

	public abstract Boolean esTaxativa();

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

	public Pair<List<Empresa>, Double> setAt0(List<Empresa> apply) {
		// TODO Auto-generated method stub
		return null;
	}

}
