package domain.condiciones.condicionesPredeterminadas;

import javax.persistence.Entity;

import org.uqbar.commons.utils.Observable;

import domain.condiciones.CondicionPredeterminada;
import domain.condiciones.CondicionTaxativa;
import domain.condiciones.OperadoresCondicion.Menor;
import domain.indicadores.indicadoresPredeterminados.Endeudamiento;

@Entity
public class TEndeudamiento extends CondicionTaxativa implements CondicionPredeterminada{
	
	public TEndeudamiento(){
		super("Endeudamiento menor a 4",new Endeudamiento(),new Menor(), 4.0);
	}
}
