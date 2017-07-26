package domain.condiciones;

import java.util.List;
import java.util.stream.Collectors;

import domain.Empresa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;

public class CondicionComparativa extends Condicion {

	private Double peso = 1.0;

	public CondicionComparativa(String nombre,Indicador indicador, OperadorCondicion operador) {
		super("Comparativa - " + nombre,indicador,operador);
	}
	
	public  CondicionComparativa(String nombre) {
		super("Comparativa - " + nombre);
	}

	@Override
	public List<Empresa> aplicarCondicion(List<Empresa> listaEmpresas, String periodo) {
		return listaEmpresas.stream().sorted((e1, e2) -> evaluarCondicion(e1, e2, periodo)).collect(Collectors.toList());
	}

	public Integer evaluarCondicion(Empresa empresaUno, Empresa empresaDos, String periodo) {
		return comparar(calcularIndicador(empresaDos, periodo), calcularIndicador(empresaUno, periodo));
	}

	public Double getPeso() {
		return peso;
	}

	public CondicionComparativa setPeso(Double peso) {
		this.peso = peso;
		return this;
	}

	@Override
	public Boolean esTaxativa() {
		return false;
	}

}
