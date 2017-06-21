package domain.indicadores;

import domain.Empresa;

public interface Indicador {

	public Double calcularIndicador(Empresa empresa, String periodo);

	public boolean suNombreEs(String nombre);

	public boolean esCustom();

	public String getNombre();

	public Object getEcuacion();
	
	public boolean esCalculable(Empresa empresa, String periodo);

}
