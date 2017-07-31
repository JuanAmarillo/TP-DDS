package domain.condiciones;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;
import domain.metodologias.EmpresaEnCalculo;

public class CondicionTaxativa extends Condicion {

	public Double valorDeComparacion;

	public CondicionTaxativa(String nombre,Indicador indicador, OperadorCondicion operador, Double valorDeComparacion) {
		super("Taxativa - " + nombre,indicador,operador);
		this.valorDeComparacion = valorDeComparacion;
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

	@Override
	public List<Empresa> aplicarCondicion(List<Empresa> empresas, String periodo) {
		return empresas.stream().filter(empresa -> evaluarCondicion(empresa, periodo))
				.collect(Collectors.toList());
	}


	@Override
	protected List<EmpresaEnCalculo> crearEmpresasEnCalculo(List<Empresa> empresasAplicadas) {
		return empresasAplicadas.stream().map(empresa -> new EmpresaEnCalculo(empresa)).collect(Collectors.toList());
	}

}
