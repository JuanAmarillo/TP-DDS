package domain.indicadores.indicadoresPredeterminados;

import domain.Empresa;
import domain.indicadores.IndicadorPredeterminado;

public class Antiguedad extends IndicadorPredeterminado{

	public Antiguedad(){
		setNombre("Antiguedad");
	}
	
	public String getEcuacion(){
		return "2017-anioFundacion";
	}
		
	@Override
	public Double calcularIndicador(Empresa empresa, String periodo){
		return (double)(2017-empresa.getAnioFundacion());
	}

	@Override
	public boolean esCalculable(Empresa empresa, String periodo) {
		return true;
	}
}
