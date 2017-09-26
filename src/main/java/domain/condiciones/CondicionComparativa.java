package domain.condiciones;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Transient;

import domain.Empresa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;
import domain.metodologias.EmpresaConPeso;

@Entity
public class CondicionComparativa extends Condicion {

	@Transient
	Double peso;

	public CondicionComparativa(String nombre, Indicador indicador, OperadorCondicion operador) {
		super(nombre, indicador, operador);
		this.peso = 0.0;
	}

	public CondicionComparativa(String nombre, Indicador indicador, OperadorCondicion operador, Double peso) {
		super(nombre, indicador, operador);
		this.peso = peso;
	}

	public Integer evaluarCondicionEnPeriodo(Empresa empresaUno, Empresa empresaDos, String periodo) {
		return comparar(calcularIndicador(empresaDos, periodo), calcularIndicador(empresaUno, periodo));
	}

	public Integer evaluarCondicion(Empresa empresaUno, Empresa empresaDos, List<String> periodos) {
		return Integer.signum(periodosEvaluados(empresaUno, empresaDos, periodos));
	}

	public int periodosEvaluados(Empresa empresaUno, Empresa empresaDos, List<String> periodos) {
		return periodos.stream().mapToInt(periodo -> evaluarCondicionEnPeriodo(empresaUno, empresaDos, periodo)).sum();
	}

	@Override
	public boolean esTaxativa() {
		return false;
	}

	@Override
	public List<EmpresaConPeso> aplicarCondicionEnPeriodo(List<EmpresaConPeso> empresasConPeso, String periodo) {
		List<EmpresaConPeso> empresasOrdenadas = this.ordenarEmpresasEnPeriodo(empresasConPeso, periodo);
		return empresasOrdenadas.stream().map(empresaConPeso -> darPeso(empresasOrdenadas, empresaConPeso))
				.collect(Collectors.toList());
	}

	@Override
	public List<EmpresaConPeso> aplicarCondicion(List<EmpresaConPeso> empresasConPeso) {
		List<EmpresaConPeso> empresasOrdenadas = this.ordenarEmpresas(empresasConPeso);
		return empresasConPeso.stream().map(empresaConPeso -> darPeso(empresasOrdenadas, empresaConPeso))
				.collect(Collectors.toList());

	}
	
	public List<EmpresaConPeso> ordenarEmpresas(List<EmpresaConPeso> empresasConPeso){
		List<String> periodos = this.obtenerPeriodos(empresasConPeso);
		return empresasConPeso.stream().sorted((empresaConPesoUno, empresaConPesoDos) -> evaluarCondicion(
				empresaConPesoUno.getEmpresa(), empresaConPesoDos.getEmpresa(), periodos))
		.collect(Collectors.toList());
	}

	public List<EmpresaConPeso> ordenarEmpresasEnPeriodo(List<EmpresaConPeso> empresasConPeso, String periodo) {
		return empresasConPeso.stream()
				.sorted((empresaConPesoUno, empresaConPesoDos) -> evaluarCondicionEnPeriodo(
						empresaConPesoUno.getEmpresa(), empresaConPesoDos.getEmpresa(), periodo))
				.collect(Collectors.toList());
	}

	public EmpresaConPeso darPeso(List<EmpresaConPeso> empresasConPeso, EmpresaConPeso empresaConPeso) {
		empresaConPeso.setPeso(empresaConPeso.getPeso()
				+ (empresasConPeso.size() - empresasConPeso.indexOf(empresaConPeso)) * this.getPeso());
		return empresaConPeso;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

}
