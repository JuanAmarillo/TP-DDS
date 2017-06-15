package interfaces;

import domain.Empresa;

public abstract class IndicadorPredeterminado implements Indicador{
	
	private String nombreIndicador;
	
	@Override
	public boolean suNombreEs(String nombre) {
		return getNombre().equals(nombre);
	}

	@Override
	public boolean esCustom() {
		return false;
	}
	
	@Override
	public boolean esCalculable(Empresa empresa, String periodo) {
		return true;
	}

	
	
	public Double calcularIndicador(Empresa emp, String periodo) {
		asignarAVariables(emp,periodo);
		return calculo();
	}
	
	public abstract void asignarAVariables(Empresa emp, String periodo);
	public abstract Double calculo();
	
	public String getNombre() {
		return nombreIndicador;
	}

	public void setNombreIndicador(String nombreIndicador) {
		this.nombreIndicador = nombreIndicador;
	}
}
