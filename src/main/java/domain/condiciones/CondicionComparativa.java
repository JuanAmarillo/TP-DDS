package domain.condiciones;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import domain.Empresa;
import net.sf.oval.exception.MethodNotFoundException;

public class CondicionComparativa extends Condicion {

	private Empresa primerEmpresa;
	private Empresa segundaEmpresa;
	
	public CondicionComparativa(String nombre){
		this.nombre = "Comparativa - " + nombre;
	}
	
	public Double primerValor(String periodo) {
		return indicador.calcularIndicador(primerEmpresa, periodo);
		
	}
	
	public Double segundoValor(String periodo) {
		return indicador.calcularIndicador(segundaEmpresa, periodo);
	}
	
	public Empresa getPrimerEmpresa() {
		return primerEmpresa;
	}

	public CondicionComparativa setPrimerEmpresa(Empresa primerEmpresa) {
		this.primerEmpresa = primerEmpresa;
		return this;
	}

	public Empresa getSegundaEmpresa() {
		return segundaEmpresa;
	}

	public CondicionComparativa setSegundaEmpresa(Empresa segundaEmpresa) {
		this.segundaEmpresa = segundaEmpresa;
		return this;
	}

	public List<Empresa> aplicarCondicion(List<Empresa> listaEmpresas, String periodo) {
		sortList(listaEmpresas, periodo);
		if(esOperadorMayor()) 
			listaEmpresas = listaEmpresas.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
		return listaEmpresas;
			
	}

	private void sortList(List<Empresa> listaEmpresas, String periodo) {
		listaEmpresas.stream()
				  .sorted((e1,e2) -> this.comparar(indicador.calcularIndicador(e1, periodo), indicador.calcularIndicador(e2, periodo)))
				  .collect(Collectors.toList());
	}

}
