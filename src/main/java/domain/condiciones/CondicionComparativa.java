package domain.condiciones;

import java.util.List;
import java.util.stream.Collectors;

import domain.Empresa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;
import domain.metodologias.EmpresaEnCalculo;

public class CondicionComparativa extends Condicion {

	private Double peso = 1.0;
	private ManejadorDePesos manejadorDePesos;

	public CondicionComparativa(String nombre, Indicador indicador, OperadorCondicion operador) {
		super("Comparativa - " + nombre, indicador, operador);
	}

	public CondicionComparativa(String nombre) {
		super("Comparativa - " + nombre);
	}

	@Override
	public List<Empresa> aplicarCondicion(List<Empresa> empresas, String periodo) {
		return empresas.stream().sorted(
				(empresaUno, empresaDos) -> evaluarCondicion(empresaUno, empresaDos, periodo))
				.collect(Collectors.toList());
	}

	public Integer evaluarCondicion(Empresa empresaUno, Empresa empresaDos, String periodo) {
		return comparar(calcularIndicador(empresaDos, periodo), calcularIndicador(empresaUno, periodo));
	}

	public CondicionComparativa setPeso(ManejadorDePesos manejadorDePesos) {
		this.manejadorDePesos = manejadorDePesos;
		return this;
	}

	@Override
	public Boolean esTaxativa() {
		return false;
	}

	@Override
	protected List<EmpresaEnCalculo> crearEmpresasEnCalculo(List<Empresa> empresasAplicadas) {
		return manejadorDePesos.agregarPeso(empresasAplicadas);
	}

}
