package domain.condiciones;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.Empresa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;
import domain.metodologias.EmpresaConPeso;

public class CondicionTaxativa extends Condicion {

	public String nombreYPeso;
	public Double peso;

	public CondicionTaxativa(String nombre, Indicador indicador, OperadorCondicion operador,
			Double valorDeComparacion) {
		super(nombre, indicador, operador);
		this.peso = 0.0;
	}

	public Boolean evaluarCondicionEnPeriodo(Empresa empresa, String periodo) {
		return comparar(calcularIndicador(empresa, periodo), peso) > 0;
	}

	public Boolean evaluarCondicion(Empresa empresa, List<String> periodos) {
		Stream<Boolean> bool = periodos.stream().map(p -> evaluarCondicionEnPeriodo(empresa, p));
		if (bool.anyMatch(e -> e == false)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<EmpresaConPeso> aplicarCondicionEnPeriodo(List<EmpresaConPeso> empresasConPeso, String periodo) {
		List<EmpresaConPeso> empr = empresasConPeso.stream()
				.filter(empresaConPeso -> evaluarCondicionEnPeriodo(empresaConPeso.getEmpresa(), periodo))
				.collect(Collectors.toList());
		return empr;
	}

	@Override
	public List<EmpresaConPeso> aplicarCondicion(List<EmpresaConPeso> empresasConPeso, List<String> periodos) {
		return empresasConPeso.stream()
				.filter(empresaConPeso -> evaluarCondicion(empresaConPeso.getEmpresa(), periodos))
				.collect(Collectors.toList());
	}

	@Override
	public Boolean esTaxativa() {
		return true;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	@Override
	public String getNombreYPeso() {
		return this.nombre + " " + this.getPeso();
	}

}
