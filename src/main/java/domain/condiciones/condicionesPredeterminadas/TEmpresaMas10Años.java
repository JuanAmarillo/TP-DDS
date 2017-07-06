package domain.condiciones.condicionesPredeterminadas;

import domain.Empresa;
import domain.condiciones.Condicion;
import domain.condiciones.CondicionTaxativa;
import domain.indicadores.indicadoresPredeterminados.Antiguedad;

public class TEmpresaMas10Años extends CondicionTaxativa {
	
	public TEmpresaMas10Años(){
		super("Empresa de mas de 10 años");
		this.esCustom = false;
		this.setValorDeComparacion(10.0);
		this.setIndicador(new Antiguedad());
	}
	
	@Override
	public int comparar(String periodo) {
		return indicador.calcularIndicador(empresa, periodo).intValue();
	}
	
	public String getEcuacion(){
		return "Antiguedad>10";
	}
}
