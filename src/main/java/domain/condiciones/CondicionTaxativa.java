package domain.condiciones;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;

import domain.Empresa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;

//@Entity
public class CondicionTaxativa extends Condicion {
	@Column(name="valor")
	public Double valorDeComparacion;

	public CondicionTaxativa(String nombre, Indicador indicador, OperadorCondicion operador,
			Double valorDeComparacion) {
		super(nombre, indicador, operador);
		this.valorDeComparacion = valorDeComparacion;
	}

	public Boolean evaluarCondicionEnPeriodo(Empresa empresa, String periodo) {
		return comparar(calcularIndicador(empresa, periodo), valorDeComparacion) > 0;
	}

	public Boolean evaluarCondicion(Empresa empresa, List<String> periodos) {
		return periodos.stream().allMatch(periodo -> evaluarCondicionEnPeriodo(empresa, periodo));
	}

	@Override
	public List<Empresa> aplicarCondicionEnPeriodo(List<Empresa> empresas, String periodo) {
		return empresas.stream().filter(empresa -> evaluarCondicionEnPeriodo(empresa, periodo))
				.collect(Collectors.toList());
	}

	@Override
	public List<Empresa> aplicarCondicion(List<Empresa> empresas) {
		return empresas.stream().filter(empresa -> evaluarCondicion(empresa, obtenerPeriodos(empresas)))
				.collect(Collectors.toList());
	}

	@Override
	public Boolean esTaxativa() {
		return true;
	}

}
