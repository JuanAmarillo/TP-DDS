package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.CondicionComparativa;
import domain.condiciones.OperadoresCondicion.Mayor;
import domain.indicadores.indicadoresPredeterminados.Antiguedad;

import org.uqbar.commons.utils.Observable;

@Observable
public class CEmpresaMayorAntiguedad extends CondicionComparativa {

	public CEmpresaMayorAntiguedad() {
		super("Mayor antiguedad");
		this.esCustom = false;
		this.setOperador(new Mayor());
		this.setIndicador(new Antiguedad());
	}	
}
