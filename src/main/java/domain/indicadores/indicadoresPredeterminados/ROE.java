package domain.indicadores.indicadoresPredeterminados;

import domain.Empresa;
import domain.indicadores.IndicadorPredeterminado;

public class ROE extends IndicadorPredeterminado{
	private double Beneficio;
	private double PatrimonioNeto;

	public ROE() {
		setNombreIndicador("ROE");
	}
	
	public Double calculo() {
		return Beneficio/PatrimonioNeto;
	}	
	
	public boolean esCalculable(Empresa empresa, String periodo) {
		return empresa.contieneLaCuentaDePeriodo("BeneficioEconomico", periodo) && 
				empresa.contieneLaCuentaDePeriodo("ActivoTotal", periodo);
	}

	public void asignarAVariables(Empresa empresa, String periodo) {
		Beneficio = empresa.getValorDeLaCuenta("Beneficio", periodo);
		PatrimonioNeto = empresa.getValorDeLaCuenta("PatrimonioNeto", periodo);
	}	
	
	public String getEcuacion() {
		return "Beneficio/PatrimonioNeto";
	}
}
