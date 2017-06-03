package domain.indicadores;

import domain.Empresa;
import externos.AnalizadorDeIndicadores;
import interfaces.IndicadorPredeterminado;

public class Pers_Sencillo extends IndicadorPredeterminado{
	
	private double cajaYBancos;
	
	public Pers_Sencillo() {
		setNombreIndicador("Pers_Sencillo");
	}
	
	public Double calculo() {
		return cajaYBancos + 23;
	}

	public void asignarAVariables(Empresa emp, String periodo) {
		cajaYBancos = new AnalizadorDeIndicadores(emp, periodo).valorDe("Caja y bancos");
	}	
	
	public String getEcuacion() {
		return "a+23";
	}
}
