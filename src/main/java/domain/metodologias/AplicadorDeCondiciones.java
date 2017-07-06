package domain.metodologias;

import java.util.List;

import domain.Empresa;
import domain.condiciones.Condicion;

public class AplicadorDeCondiciones extends Metodologia {

	private Metodologia cadenaCondiciones;
	private Condicion condicionAAplicar;
	
	public AplicadorDeCondiciones(Metodologia cadenaCondiciones, Condicion condicionAAplicar) {
		this.cadenaCondiciones = cadenaCondiciones;
		this.condicionAAplicar = condicionAAplicar;
	}

	@Override
	public List<Empresa> aplicarMetodologia(List<Empresa> listaEmpresas, String periodo) {
		return condicionAAplicar.aplicarCondicion(cadenaCondiciones.aplicarMetodologia(listaEmpresas, periodo), periodo);
	}
	
}
