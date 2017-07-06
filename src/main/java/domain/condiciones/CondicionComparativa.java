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
		sortList(listaEmpresas, periodo);
		return listaEmpresas;
	}

	private void sortList(List<Empresa> listaEmpresas, String periodo) {
		listaEmpresas.stream()
				  .sorted((e1,e2) -> filterMethod(e1, e2, periodo))
				  .collect(Collectors.toList());
	}

	public int filterMethod(Empresa e1, Empresa e2, String periodo) {
		return this.comparar(indicador.calcularIndicador(e1, periodo), indicador.calcularIndicador(e2, periodo));
	}

}
