package domain.condiciones;

import java.util.List;
import java.util.stream.Collectors;

import domain.Empresa;

public class CondicionTaxativa extends Condicion {

	public Double valorDeComparacion;

	public CondicionTaxativa(String nombre) {
		this.nombre = "Taxativa - " + nombre;
	}
	
	public List<Empresa> aplicarCondicion(List<Empresa> listaEmpresas, String periodo){
		return listaEmpresas.stream()
					 .filter(empresa -> aplicarComparacion(empresa, periodo))
					 .collect(Collectors.toList());
	}
	
	public  boolean aplicarComparacion(Empresa empresa, String periodo) {
		
		return comparar(indicador.calcularIndicador(empresa, periodo), valorDeComparacion) > 0;
	}

	public Double getValorDeComparacion() {
		return valorDeComparacion;
	}

	public void setValorDeComparacion(Double valorDeComparacion) {
		this.valorDeComparacion = valorDeComparacion;
	}
}
