package domain.condiciones.condicionesPredeterminadas;

import domain.Empresa;
import domain.condiciones.Condicion;
import domain.condiciones.CondicionTaxativa;

public class TEmpresaMas10Años extends CondicionTaxativa {
	
	@Override
	public boolean comparar() {
		return empresa.antiguedad() > 10;
	}
	
	public TEmpresaMas10Años(){
		setNombre("EmpresaDeMasDe10Anios");
	}
	
	public String getEcuacion(){
		return "Antiguedad>10";
	}
}
