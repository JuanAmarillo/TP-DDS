package domain.indicadores;

import domain.Empresa;
import interfaces.IndicadorPredeterminado;

public class Pers_Sencillo extends IndicadorPredeterminado{
	
	private double cajaYBancos;
	
	public Pers_Sencillo() {
		setNombreIndicador("Pers_Sencillo");
	}
	
	public Double calculo() {
		return cajaYBancos + 23;
	}
	
	public boolean esCalculable(Empresa empresa, String periodo) {
		return empresa.contieneLaCuentaDePeriodo("Caja y bancos", periodo);
	}

	public void asignarAVariables(Empresa empresa, String periodo) {
		cajaYBancos = empresa.getValorDeLaCuenta("Caja y bancos", periodo);
	}	
	
	public String getEcuacion() {
		return "Caja y bancos+23";
	}
}
