package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionPredeterminada;
import domain.condiciones.OperadoresCondicion.Mayor;
import domain.indicadores.indicadoresPredeterminados.Endeudamiento;
import org.uqbar.commons.utils.Observable;

@Observable
public class CEndeudamiento extends CondicionComparativa implements CondicionPredeterminada{

	public CEndeudamiento() {
		super("Endeudamiento");
		this.setOperador(new Mayor());
		this.setIndicador(new Endeudamiento());
	}
	

}
