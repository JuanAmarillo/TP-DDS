package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionPredeterminada;

import org.uqbar.commons.utils.Observable;

@Observable
public class CMargenesCrecientes extends CondicionComparativa implements CondicionPredeterminada {

	public CMargenesCrecientes() {
		super("Margenes Crecientes");
	}

}
