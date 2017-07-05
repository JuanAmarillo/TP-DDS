package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.CondicionComparativa;

public class CEmpresaMayorAntiguedad extends CondicionComparativa {

	@Override
	public boolean comparar() {
		return getPrimerEmpresa().antiguedad() > getSegundaEmpresa().antiguedad();
	}
	
	
}
