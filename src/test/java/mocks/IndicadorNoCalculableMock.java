package mocks;

import domain.Empresa;
import domain.indicadores.IndicadorPredeterminado;

public class IndicadorNoCalculableMock extends IndicadorPredeterminado{

	@Override
	public Double calcularIndicador(Empresa empresa, String periodo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean esCalculable(Empresa empresa, String periodo) {
		
		return false;
	}

	@Override
	public String getEcuacion() {
		// TODO Auto-generated method stub
		return null;
	}

}
