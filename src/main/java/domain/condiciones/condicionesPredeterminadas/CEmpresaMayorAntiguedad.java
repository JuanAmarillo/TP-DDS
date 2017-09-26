package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionPredeterminada;
import domain.condiciones.OperadoresCondicion.Mayor;
import domain.indicadores.indicadoresPredeterminados.Antiguedad;

import org.uqbar.commons.utils.Observable;

@Observable
public class CEmpresaMayorAntiguedad extends CondicionComparativa implements CondicionPredeterminada{

	public CEmpresaMayorAntiguedad() {
		super("Mayor antiguedad", new Antiguedad(), new Mayor());
	}
	
	public CEmpresaMayorAntiguedad(Double peso){
		super("Mayor antiguedad", new Antiguedad(), new Mayor(), peso);
	}
	
}
