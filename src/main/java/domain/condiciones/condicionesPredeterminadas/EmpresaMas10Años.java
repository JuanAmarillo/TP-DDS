package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.Condicion;
import domain.condiciones.CondicionTaxativa;

public class EmpresaMas10Años extends CondicionTaxativa {
	
	@Override
	public boolean comparar() {
		return empresa.antiguedad() > 10;
	}
	
	public EmpresaMas10Años(){
		setNombre("EmpresaDeMasDe10Anios");
	}
	
	public String getEcuacion(){
		return "Antiguedad>10";
	}
	

}
