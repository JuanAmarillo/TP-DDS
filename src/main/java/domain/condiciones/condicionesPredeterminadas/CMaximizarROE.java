package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.CondicionComparativa;

public class CMaximizarROE extends CondicionComparativa {
	
	public CMaximizarROE(){
		setNombre("Comparativa - Maximizar ROE");
		setOrigen(false);
	}
	
	@Override
	public boolean comparar(){
		return true;
	}

}
