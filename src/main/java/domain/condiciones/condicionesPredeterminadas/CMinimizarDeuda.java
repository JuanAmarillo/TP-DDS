package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.CondicionComparativa;
import org.uqbar.commons.utils.Observable;

@Observable
public class CMinimizarDeuda extends CondicionComparativa {

	public CMinimizarDeuda() {
		super("Minimizar Deuda");
		this.esCustom = false;
	}

}
