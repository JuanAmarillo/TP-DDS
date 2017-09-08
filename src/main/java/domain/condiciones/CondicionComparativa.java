package domain.condiciones;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.Empresa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;

public class CondicionComparativa extends Condicion {

	private Double peso;
	
	public CondicionComparativa(String nombre, Indicador indicador, OperadorCondicion operador) {
		super(nombre, indicador, operador);
	}

	public Integer evaluarCondicionEnPeriodo(Empresa empresaUno, Empresa empresaDos, String periodo) {
		return comparar(calcularIndicador(empresaDos, periodo), calcularIndicador(empresaUno, periodo));
	}

	public Integer evaluarCondicion(Empresa empresaUno, Empresa empresaDos, List<String> periodos) {
		Stream<Integer> retornos = periodos.stream()
				.map(periodo -> evaluarCondicionEnPeriodo(empresaUno, empresaDos, periodo));
		if (retornos.anyMatch(e -> e.equals(1))) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public List<Empresa> aplicarCondicionEnPeriodo(List<Empresa> empresas, String periodo) {
		return empresas.stream()
				.sorted((empresaUno, empresaDos) -> evaluarCondicionEnPeriodo(empresaUno, empresaDos, periodo))
				.collect(Collectors.toList());
	}

	@Override
	public List<Empresa> aplicarCondicion(List<Empresa> empresas) {
		List<String> periodos = empresas.stream().map(empresa -> empresa.getPeriodos()).flatMap(Set::stream)
				.collect(Collectors.toList());
		return empresas.stream().sorted((empresaUno, empresaDos) -> evaluarCondicion(empresaUno, empresaDos, periodos))
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
