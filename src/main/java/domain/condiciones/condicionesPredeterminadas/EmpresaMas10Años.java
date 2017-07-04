package domain.condiciones.condicionesPredeterminadas;

import domain.condiciones.Condicion;

public class EmpresaMas10Años extends Condicion {
	
	public EmpresaMas10Años(){
		setNombre("EmpresaDeMasDe10Anios");
	}
	
	public String getEcuacion(){
		return "Antiguedad>10";
	}
	

}
