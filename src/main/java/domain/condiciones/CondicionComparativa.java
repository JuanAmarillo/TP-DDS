package domain.condiciones;

import java.util.List;
import java.util.stream.Collectors;

import domain.Empresa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;
import domain.metodologias.EmpresaEnCalculo;

public class CondicionComparativa extends Condicion {

	private ManejadorDePesos manejadorDePesos;

	public CondicionComparativa(String nombre, Indicador indicador, OperadorCondicion operador) {
		super(nombre, indicador, operador);
	}

	public CondicionComparativa setManejadorDePesos(ManejadorDePesos manejadorDePesos) {
		this.manejadorDePesos = manejadorDePesos;
		return this; // sacar eso luego de hacer bien los test
	}

	@Override
	public List<Empresa> aplicarCondicion(List<Empresa> empresas, String periodo) {
		return empresas.stream().sorted((empresaUno, empresaDos) -> evaluarCondicion(empresaUno, empresaDos, periodo))
				.collect(Collectors.toList());
	}

	public Integer evaluarCondicion(Empresa empresaUno, Empresa empresaDos, String periodo) {
		return comparar(calcularIndicador(empresaDos, periodo), calcularIndicador(empresaUno, periodo));
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
