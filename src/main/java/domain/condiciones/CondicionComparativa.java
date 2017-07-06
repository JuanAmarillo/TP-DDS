package domain.condiciones;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import domain.Empresa;
import net.sf.oval.exception.MethodNotFoundException;

public class CondicionComparativa extends Condicion {

	
	public CondicionComparativa(String nombre){
		this.nombre = "Comparativa - " + nombre;
	}

	public List<Empresa> aplicarCondicion(List<Empresa> listaEmpresas, String periodo) {
		return sortList(listaEmpresas, periodo);
	}

	private List<Empresa> sortList(List<Empresa> listaEmpresas, String periodo) {
		List<Empresa> listaEmpresasSorteadas = listaEmpresas.stream()
				  .sorted((e1,e2) -> sortMethod(e1, e2, periodo))
				  .collect(Collectors.toList());
		return listaEmpresasSorteadas;
	}

	public int sortMethod(Empresa e1, Empresa e2, String periodo) {
		checkearCalculabilidad(e1,periodo);
		checkearCalculabilidad(e2,periodo);
		return this.comparar(indicador.calcularIndicador(e1, periodo), indicador.calcularIndicador(e2, periodo));
		
	}
	public boolean esComparativa() {
		return true;
	}
	public boolean esTaxativa() {
		return false;
	}

}
