package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.CondicionPredeterminada;
import domain.condiciones.CondicionTaxativa;
import domain.condiciones.OperadoresCondicion.Menor;
import domain.indicadores.indicadoresPredeterminados.Endeudamiento;

public class TEndeudamiento extends CondicionTaxativa implements CondicionPredeterminada{
	
	public TEndeudamiento(){
		super("Empresa de mas de 10 años");
		this.setValorDeComparacion(0.4);
		this.setIndicador(new Endeudamiento());
		this.setOperador(new Menor());
	}
	
	public String getEcuacion(){
		return "Endeudamiento<15";
	}
}
