package domain.condiciones;

import java.util.List;
import java.util.stream.Collectors;

import domain.Empresa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;

public class CondicionComparativa extends Condicion {

	public CondicionComparativa(String nombre, Indicador indicador, OperadorCondicion operador) {
		super(nombre, indicador, operador);
	}

	public List<Empresa> aplicarCondicion(List<Empresa> empresas, String periodo) {
		return empresas.stream()
				.sorted((empresaUno, empresaDos) -> evaluarCondicion(empresaUno, empresaDos, periodo))
				.collect(Collectors.toList());
	}

	public Integer evaluarCondicion(Empresa empresaUno, Empresa empresaDos, String periodo) {
		return comparar(calcularIndicador(empresaDos, periodo), calcularIndicador(empresaUno, periodo));
	}
/*
	public List<Empresa> aplicarCondicionEnTodosLosPeriodos(List<Empresa> empresas, List<String> periodos) {
		return empresas.stream().sorted((empresaUno, empresaDos) -> evaluarCondicion(empresaUno, empresaDos, periodos))
				.collect(Collectors.toList());


	public Integer evaluarCondicion(Empresa empresaUno, Empresa empresaDos, List<String> periodos) {
		int total = 0;
		for (int i = 0; i <= periodos.size(); i++) {
			int resultado = evaluarCondicionEnPeriodo(empresaUno, empresaDos, periodos.get(i));
			total += resultado;
		}
		return total;
	}
*/
	@Override
	public Boolean esTaxativa() {
		return false;
}
}
