package domain.indicadores.indicadoresPredeterminados;

import javax.persistence.Entity;

import org.joda.time.DateTime;

import domain.Empresa;
import domain.indicadores.IndicadorPredeterminado;

@Entity
public class Antiguedad extends IndicadorPredeterminado{

	public Antiguedad(){
		setNombre("Antiguedad");
	}
		
	@Override
	public Double calcularIndicador(Empresa empresa, String periodo){
		return (double)(new DateTime().getYear() -empresa.getAnioFundacion());
	}
}
