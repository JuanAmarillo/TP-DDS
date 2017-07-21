package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.CondicionComparativa;
import domain.condiciones.OperadoresCondicion.Mayor;
import domain.indicadores.indicadoresPredeterminados.ROE;
import org.uqbar.commons.utils.Observable;

@Observable
public class CMaximizarROE extends CondicionComparativa {
	
	public CMaximizarROE(){
		super("Maximizar ROE");
		this.esCustom = false;
		this.setOperador(new Mayor());
		this.setIndicador(new ROE());
	}

}
