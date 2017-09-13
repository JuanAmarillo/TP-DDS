package domain.condiciones;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.Transient;

import domain.Empresa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;

@Entity
public class CondicionComparativa extends Condicion {
	@Transient
	private Double peso;
	
	public CondicionComparativa(String nombre, Indicador indicador, OperadorCondicion operador) {
		super(nombre, indicador, operador);
	}

	public Integer evaluarCondicionEnPeriodo(Empresa empresaUno, Empresa empresaDos, String periodo) {
		return comparar(calcularIndicador(empresaDos, periodo), calcularIndicador(empresaUno, periodo));
	}

	public Integer evaluarCondicion(Empresa empresaUno, Empresa empresaDos, List<String> periodos) {
		return Integer.signum(periodosEvaluados(empresaUno, empresaDos, periodos));
	}

	public int periodosEvaluados(Empresa empresaUno, Empresa empresaDos, List<String> periodos) {
		return periodos.stream()
				.mapToInt(periodo -> evaluarCondicionEnPeriodo(empresaUno, empresaDos, periodo))
				.sum();
	}

	@Override
	public List<Empresa> aplicarCondicionEnPeriodo(List<Empresa> empresas, String periodo) {
		return empresas.stream()
				.sorted((empresaUno, empresaDos) -> evaluarCondicionEnPeriodo(empresaUno, empresaDos, periodo))
				.collect(Collectors.toList());
	}

	@Override
	public List<Empresa> aplicarCondicion(List<Empresa> empresas) {
		return empresas.stream().sorted((empresaUno, empresaDos) -> evaluarCondicion(empresaUno, empresaDos, obtenerPeriodos(empresas)))
				.collect(Collectors.toList());
	}

	@Override
	public Boolean esTaxativa() {
		return false;
	}

	public Double getPeso() {
		return peso;
	}

	public CondicionComparativa setPeso(Double peso) {
		this.peso = peso;
		return this;
	}
}
