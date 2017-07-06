package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.CondicionComparativa;
import domain.indicadores.indicadoresPredeterminados.ROE;

public class CMaximizarROE extends CondicionComparativa {
	
	public CMaximizarROE(){
		super("Maximizar ROE");
		this.esCustom = false;
		this.setOperador(">");
		this.setIndicador(new ROE());
	}

}
