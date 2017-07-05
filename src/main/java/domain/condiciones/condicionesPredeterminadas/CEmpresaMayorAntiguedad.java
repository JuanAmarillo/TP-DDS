package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.CondicionComparativa;

public class CEmpresaMayorAntiguedad extends CondicionComparativa {

	public CEmpresaMayorAntiguedad() {
		setNombre("Comparativa - Mayor antiguedad");
		setOrigen(false);
	}
	
	@Override
	public boolean comparar() {
		return getPrimerEmpresa().antiguedad() > getSegundaEmpresa().antiguedad();
	}
	
	
}
