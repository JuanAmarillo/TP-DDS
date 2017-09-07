package domain.condiciones;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.Empresa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;
import domain.metodologias.EmpresaConPeso;

public class CondicionComparativa extends Condicion {
	
	public String nombreYPeso;
	Double peso;

	public CondicionComparativa(String nombre, Indicador indicador, OperadorCondicion operador) {
		super(nombre, indicador, operador);
	}

	public CondicionComparativa(String nombre, Indicador indicador, OperadorCondicion operador, Double peso) {
		super(nombre, indicador, operador);
		this.peso = peso;
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
	public List<EmpresaConPeso> aplicarCondicionEnPeriodo(List<EmpresaConPeso> empresasConPeso, String periodo) {
		List<EmpresaConPeso> empresasOrdenadas = this.ordenarEmpresas(empresasConPeso, periodo);
		return empresasOrdenadas.stream().map(empresaConPeso -> darPeso(empresasOrdenadas, empresaConPeso))
				.collect(Collectors.toList());
	}

	@Override
	public List<EmpresaConPeso> aplicarCondicion(List<EmpresaConPeso> empresasConPeso) {
		List<String> periodos = empresasConPeso.stream().map(empresa -> empresa.getEmpresa().getPeriodos())
				.flatMap(Set::stream).collect(Collectors.toList());
		return empresasConPeso.stream().sorted((empresaUno, empresaDos) -> evaluarCondicion(empresaUno.getEmpresa(),
				empresaDos.getEmpresa(), periodos)).collect(Collectors.toList());
	}

	@Override
	public Boolean esTaxativa() {
		return false;
	}

	public List<EmpresaConPeso> ordenarEmpresas(List<EmpresaConPeso> empresasConPeso, String periodo) {
		return empresasConPeso.stream()
				.sorted((empresaConPesoUno, empresaConPesoDos) -> evaluarCondicionEnPeriodo(
						empresaConPesoUno.getEmpresa(), empresaConPesoDos.getEmpresa(), periodo))
				.collect(Collectors.toList());
	}

	public EmpresaConPeso darPeso(List<EmpresaConPeso> empresasConPeso, EmpresaConPeso empresaConPeso) {
		empresaConPeso.setPeso(
				empresaConPeso.getPeso()
				+ 
				((empresasConPeso.size() - empresasConPeso.indexOf(empresaConPeso)) * this.getPeso())
				);
		return empresaConPeso;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	@Override
	public String getNombreYPeso(){
		return this.nombre+" "+this.getPeso();
	}

}
