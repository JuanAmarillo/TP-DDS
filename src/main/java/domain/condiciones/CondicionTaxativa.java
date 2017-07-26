package domain.condiciones;

import java.util.List;
import java.util.stream.Collectors;

import domain.Empresa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;

public class CondicionTaxativa extends Condicion {

	public Double valorDeComparacion;

	public CondicionTaxativa(String nombre,Indicador indicador, OperadorCondicion operador, Double valorDeComparacion) {
		super("Taxativa - " + nombre,indicador,operador);
		this.valorDeComparacion = valorDeComparacion;
	}
	
	public CondicionTaxativa(String nombre) {
		super("Taxativa - " + nombre);
	}

	@Override
	public List<Empresa> aplicarCondicion(List<Empresa> listaEmpresas, String periodo) {
		return listaEmpresas.stream().filter(empresa -> evaluarCondicion(empresa, periodo))
				.collect(Collectors.toList());
	}

	public Boolean evaluarCondicion(Empresa empresa, String periodo) {
		return comparar(calcularIndicador(empresa, periodo), valorDeComparacion) > 0;
	}

	public Double getValorDeComparacion() {
		return valorDeComparacion;
	}

	public void setValorDeComparacion(Double valorDeComparacion) {
		this.valorDeComparacion = valorDeComparacion;
	}

	@Override
	public Boolean esTaxativa() {
		return true;
	}

}
