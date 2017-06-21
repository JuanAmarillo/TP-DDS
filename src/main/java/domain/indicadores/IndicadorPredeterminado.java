package domain.indicadores;

import domain.Empresa;
import domain.repositorios.RepositorioIndicadores;

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
	
	
	public Double calcularIndicador(Empresa empresa, String periodo) {
		asignarAVariables(empresa,periodo);
		return calculo();
	}
	
	protected boolean indicadorCalculable(String indicador,Empresa empresa, String periodo){
		if(existeIndicador(indicador))
			return esPosibleCalcularlo(indicador, empresa, periodo);
		else
			return existeIndicador(indicador);
	}

	private boolean esPosibleCalcularlo(String indicador, Empresa empresa, String periodo) {
		return buscarIndicador(indicador).esCalculable(empresa, periodo);
	}

	protected Indicador buscarIndicador(String indicador) {
		return RepositorioIndicadores.instance().buscarIndicador(indicador);
	}

	private boolean existeIndicador(String indicador) {
		return RepositorioIndicadores.instance().contieneElIndicador(indicador);
	}
	
	public abstract void asignarAVariables(Empresa empresa, String periodo);
	public abstract Double calculo();
	public abstract boolean esCalculable(Empresa empresa, String periodo);
	
	public String getNombre() {
		return nombreIndicador;
	}

	public void setNombreIndicador(String nombreIndicador) {
		this.nombreIndicador = nombreIndicador;
	}
}
