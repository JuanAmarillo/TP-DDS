package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.CondicionComparativa;
import org.uqbar.commons.utils.Observable;

@Observable
public class CMargenesCrecientes extends CondicionComparativa {

	public CMargenesCrecientes() {
		super("Margenes Crecientes");
		this.esCustom = false;
	}

}
