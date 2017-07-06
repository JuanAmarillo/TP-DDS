package domain.condiciones.condicionesPredeterminadas;

import domain.Empresa;
import domain.condiciones.Condicion;
import domain.condiciones.CondicionTaxativa;
import domain.condiciones.OperadoresCondicion.Mayor;
import domain.indicadores.indicadoresPredeterminados.Antiguedad;

public class TEmpresaMas10Años extends CondicionTaxativa {
	
	public TEmpresaMas10Años(){
		super("Empresa de mas de 10 años");
		this.esCustom = false;
		this.setValorDeComparacion(10.0);
		this.setIndicador(new Antiguedad());
		this.setOperador(new Mayor());
	}
	
	public String getEcuacion(){
		return "Antiguedad>10";
	}
}
