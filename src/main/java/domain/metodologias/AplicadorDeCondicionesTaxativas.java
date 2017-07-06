package domain.metodologias;

import java.util.List;

import domain.Empresa;
import domain.condiciones.CondicionTaxativa;

public class AplicadorDeCondicionesTaxativas extends Metodologia {
	
	private Metodologia cadenaCondiciones;
	private CondicionTaxativa condicionAAplicar;

	public AplicadorDeCondicionesTaxativas(Metodologia cadenaCondiciones, CondicionTaxativa condicionAAplicar) {
		this.cadenaCondiciones = cadenaCondiciones;
		this.condicionAAplicar = condicionAAplicar;
	}

	@Override
	public List<Empresa> aplicarMetodologia(List<Empresa> listaEmpresas, String periodo) {
		return cadenaCondiciones.aplicarMetodologia(condicionAAplicar.aplicarCondicion(listaEmpresas, periodo), periodo);
	}

}
