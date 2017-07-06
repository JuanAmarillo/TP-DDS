package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.CondicionComparativa;
import domain.indicadores.indicadoresPredeterminados.Endeudamiento;

public class CEndeudamiento extends CondicionComparativa{

	public CEndeudamiento() {
		super("Endeudamiento");
		this.esCustom = false;
		this.setOperador(">");
		this.setIndicador(new Endeudamiento());
	}
	

}
