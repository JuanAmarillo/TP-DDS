package domain.metodologias;

import java.util.List;

import domain.Empresa;
import domain.condiciones.CondicionComparativa;

public class AplicadorDeCondicionesTaxativas implements Metodologia {

	public AplicadorDeCondicionesTaxativas(Metodologia cadenaCondiciones, CondicionComparativa condicionAAplicar) {
		super();
		this.cadenaCondiciones = cadenaCondiciones;
		this.condicionAAplicar = condicionAAplicar;
	}

	private Metodologia cadenaCondiciones;
	private CondicionComparativa condicionAAplicar;
	
	@Override
	public List<Empresa> aplicarMetodologia(List<Empresa> listaEmpresas, String periodo) {
		return cadenaCondiciones.aplicarMetodologia(condicionAAplicar.aplicarCondicion(listaEmpresas, periodo), periodo);
	}

}
