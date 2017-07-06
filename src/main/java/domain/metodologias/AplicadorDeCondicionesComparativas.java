package domain.metodologias;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import domain.Empresa;
import domain.condiciones.CondicionComparativa;

public class AplicadorDeCondicionesComparativas implements Metodologia {

	private Metodologia cadenaCondiciones;
	private CondicionComparativa condicionAAplicar;
	
	public AplicadorDeCondicionesComparativas(Metodologia cadenaCondiciones, CondicionComparativa condicionAAplicar) {
		this.cadenaCondiciones = cadenaCondiciones;
		this.condicionAAplicar = condicionAAplicar;
	}

	@Override
	public List<Empresa> aplicarMetodologia(List<Empresa> listaEmpresas, String periodo) {
		return condicionAAplicar.aplicarCondicion(cadenaCondiciones.aplicarMetodologia(listaEmpresas, periodo), periodo);
	}
	
}
