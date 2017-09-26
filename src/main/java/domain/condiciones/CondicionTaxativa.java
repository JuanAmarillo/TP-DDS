package domain.condiciones;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;

import domain.Empresa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;
import domain.metodologias.EmpresaConPeso;

//@Entity
public class CondicionTaxativa extends Condicion {

	@Column(name="valor")
	public Double valorDeComparacion;

	public CondicionTaxativa(String nombre, Indicador indicador, OperadorCondicion operador,
			Double valorDeComparacion) {
		super(nombre, indicador, operador);
		this.valorDeComparacion = valorDeComparacion;
	}

	public Boolean evaluarCondicionEnPeriodo(Empresa empresa, String periodo) {
		return comparar(calcularIndicador(empresa, periodo), valorDeComparacion) > 0;
	}

	public Boolean evaluarCondicion(Empresa empresa, List<String> periodos) {
		return periodos.stream().allMatch(periodo -> evaluarCondicionEnPeriodo(empresa, periodo));
}
	
	@Override
	public boolean esTaxativa() {
		return true;
	}

	
	public List<EmpresaConPeso> aplicarCondicionEnPeriodo(List<EmpresaConPeso> empresasConPeso, String periodo) {
		return empresasConPeso.stream().filter(empresaConPeso -> evaluarCondicionEnPeriodo(empresaConPeso.getEmpresa(), periodo))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<EmpresaConPeso> aplicarCondicion(List<EmpresaConPeso> empresasConPeso){
		 return empresasConPeso.stream().filter(empresaConPeso -> evaluarCondicion(empresaConPeso.getEmpresa(), obtenerPeriodos(empresasConPeso)))
					.collect(Collectors.toList());
		
	}

	
}
