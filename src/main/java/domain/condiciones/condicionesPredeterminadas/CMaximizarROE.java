package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.CondicionComparativa;

public class CMaximizarROE extends CondicionComparativa {
	
	public CMaximizarROE(){
		super("Maximizar ROE");
		this.esCustom = false;
	}
	
	@Override
	public boolean comparar(){
		return true;
	}

}
