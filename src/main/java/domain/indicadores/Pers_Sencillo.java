package domain.indicadores;

import calculoIndicadores.ConstructoresIndicador.Analizador;
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

	public void asignarAVariables(Empresa emp, String periodo) {
		cajaYBancos = new Analizador(emp, periodo).scan("Caja y bancos").compilar().calcularValor(emp, periodo);
	}	
	
	public String getEcuacion() {
		return "a+23";
	}
}
