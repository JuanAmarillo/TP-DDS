package domain.metodologias;

import java.util.List;

import domain.Empresa;
import domain.condiciones.CondicionComparativa;

public class AplicadorDeCondicionesComparativas extends Metodologia {

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
