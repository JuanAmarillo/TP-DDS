package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionPredeterminada;
import domain.condiciones.OperadoresCondicion.Mayor;
import domain.indicadores.indicadoresPredeterminados.ROE;
import org.uqbar.commons.utils.Observable;

@Observable
public class CMaximizarROE extends CondicionComparativa implements CondicionPredeterminada {
	
	public CMaximizarROE(){
		super("Maximizar ROE",new ROE(), new Mayor());
	}
	
	public CMaximizarROE(Double peso){
		super("Maximizar ROE",new ROE(), new Mayor(),peso);
	}

}
