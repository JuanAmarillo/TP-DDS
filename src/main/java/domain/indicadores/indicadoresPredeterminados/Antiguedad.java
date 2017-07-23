package domain.indicadores.indicadoresPredeterminados;

import org.joda.time.DateTime;

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
		return (double)(new DateTime().getYear() -empresa.getAnioFundacion());
	}
}
