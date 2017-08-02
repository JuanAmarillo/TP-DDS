package domain.condiciones;

import java.util.List;
import java.util.stream.Collectors;

import domain.Empresa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;
import domain.metodologias.EmpresaEnCalculo;
import exceptions.NoSePuedeCalcularException;

public abstract class Condicion implements CondicionCustom {

	protected String nombre;
	protected Indicador indicador;
	protected OperadorCondicion operador;

	public Condicion(String nombre, Indicador indicador, OperadorCondicion operador) {
		this.nombre = nombre;
		this.indicador = indicador;
		this.operador = operador;
	}

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

	public List<EmpresaEnCalculo> apply(List<Empresa> empresas, String periodo) {
		List<Empresa> empresasAplicadas = aplicarCondicion(empresas, periodo);
		return crearEmpresasEnCalculo(empresasAplicadas);
	}

	protected abstract List<EmpresaEnCalculo> crearEmpresasEnCalculo(List<Empresa> empresasAplicadas);

	public abstract List<Empresa> aplicarCondicion(List<Empresa> empresas, String periodo);

	public abstract Boolean esTaxativa();

}
