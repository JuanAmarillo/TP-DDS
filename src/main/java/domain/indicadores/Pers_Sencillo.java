package domain.indicadores;

import domain.Empresa;
import externos.AnalizadorDeIndicadores;
import interfaces.IndicadorPredeterminado;

public class Pers_Sencillo extends IndicadorPredeterminado{
	
	private double a;
	
	public Pers_Sencillo() {
		setNombreIndicador("Pers_Sencillo");
	}
	
	@Override
	public Double calcularIndicador(Empresa emp, String periodo) {
		asignarAVariables(emp,periodo);
		return a + 23;
	}

	@Override
	public String getEcuacion() {
		return "a+23";
	}

	private void asignarAVariables(Empresa emp, String periodo) {
		a = new AnalizadorDeIndicadores(emp, periodo).valorDe("a");
	}	
}
