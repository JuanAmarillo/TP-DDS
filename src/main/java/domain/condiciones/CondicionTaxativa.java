package domain.condiciones;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import domain.Empresa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;

public class CondicionTaxativa extends Condicion {

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
		return !periodos.stream().map(p -> evaluarCondicionEnPeriodo(empresa, p)).anyMatch(e -> e == false);
	}

	@Override
	public List<Empresa> aplicarCondicionEnPeriodo(List<Empresa> empresas, String periodo) {
		return empresas.stream().filter(empresa -> evaluarCondicionEnPeriodo(empresa, periodo))
				.collect(Collectors.toList());
	}

	@Override
	public List<Empresa> aplicarCondicion(List<Empresa> empresas) {
		List<String> periodos = empresas.stream().map(empresa -> empresa.getPeriodos()).flatMap(Set::stream)
				.collect(Collectors.toList());
		return empresas.stream().filter(empresa -> evaluarCondicion(empresa, periodos)).collect(Collectors.toList());
	}

	@Override
	public Boolean esTaxativa() {
		return true;
	}

}
