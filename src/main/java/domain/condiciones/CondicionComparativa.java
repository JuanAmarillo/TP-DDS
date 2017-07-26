package domain.condiciones;

import java.util.List;
import java.util.stream.Collectors;

import domain.Empresa;

public class CondicionComparativa extends Condicion{

	private Double peso = 1.0;
	
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
		return this.comparar(indicador.calcularIndicador(e2, periodo), indicador.calcularIndicador(e1, periodo));
		
	}

	public Double getPeso() {
		return peso;
	}
	
	public CondicionComparativa setPeso(Double valor) {
		this.peso = valor;
		return this;
	}

	@Override
	public Boolean esTaxativa() {
		return false;
	}

}
