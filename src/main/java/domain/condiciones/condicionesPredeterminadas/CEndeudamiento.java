package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionPredeterminada;
import domain.condiciones.OperadoresCondicion.Mayor;
import domain.indicadores.indicadoresPredeterminados.Endeudamiento;

import javax.persistence.Entity;

import org.uqbar.commons.utils.Observable;

@Entity
public class CEndeudamiento extends CondicionComparativa implements CondicionPredeterminada {

	public CEndeudamiento() {
		super("Endeudamiento", new Endeudamiento(), new Mayor());
	}

	public CEndeudamiento(Double peso) {
		super("Endeudamiento", new Endeudamiento(), new Mayor(), peso);
	}

}
