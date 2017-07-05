package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.CondicionComparativa;

public class CEmpresaMayorAntiguedad extends CondicionComparativa {

	public CEmpresaMayorAntiguedad() {
		super("Mayor antiguedad");
		this.esCustom = false;
	}
	
	@Override
	public boolean comparar() {
		return getPrimerEmpresa().antiguedad() > getSegundaEmpresa().antiguedad();
	}
	
	
}
