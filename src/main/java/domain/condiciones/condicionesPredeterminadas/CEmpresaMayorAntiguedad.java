package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.CondicionComparativa;
import domain.indicadores.indicadoresPredeterminados.Antiguedad;

public class CEmpresaMayorAntiguedad extends CondicionComparativa {

	public CEmpresaMayorAntiguedad() {
		super("Mayor antiguedad");
		this.esCustom = false;
		this.setOperador(">");
		this.setIndicador(new Antiguedad());
	}	
}
