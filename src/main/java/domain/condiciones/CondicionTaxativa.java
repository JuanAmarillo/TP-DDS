package domain.condiciones;

import java.util.List;
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

	public Boolean evaluarCondicion(Empresa empresa, String periodo) {
		return comparar(calcularIndicador(empresa, periodo), valorDeComparacion) > 0;
	}

	@Override
	public Boolean esTaxativa() {
		return true;
	}

	@Override
	public List<Empresa> aplicarCondicion(List<Empresa> empresas, String periodo) {
		return empresas.stream().filter(empresa -> evaluarCondicion(empresa, periodo)).collect(Collectors.toList());
}
}
