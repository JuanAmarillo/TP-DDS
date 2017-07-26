package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionPredeterminada;

import org.uqbar.commons.utils.Observable;

@Observable
public class CMinimizarDeuda extends CondicionComparativa implements CondicionPredeterminada {

	public CMinimizarDeuda() {
		super("Minimizar Deuda");
	}

}
