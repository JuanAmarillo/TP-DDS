package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.CondicionPredeterminada;
import domain.condiciones.CondicionTaxativa;
import domain.condiciones.OperadoresCondicion.Mayor;
import domain.indicadores.indicadoresPredeterminados.Antiguedad;
import org.uqbar.commons.utils.Observable;

@Observable
public class TEmpresaMas10Años extends CondicionTaxativa implements CondicionPredeterminada {
	
	public TEmpresaMas10Años(){
		super("Empresa de mas de 10 años",new Antiguedad(), new Mayor(), 10.0);
	}
	
	public String getEcuacion(){
		return "Antiguedad>10";
	}
}
