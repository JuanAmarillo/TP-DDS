package domain.condiciones;

import domain.indicadores.Indicador;
import domain.repositorios.RepositorioIndicadores;

public abstract class BuilderCondicion {
	protected String nombre;
	protected String operador;
	protected Double value;
	protected Indicador indicador;
	
	public  void setNombre(String nombre){
		if(!nombre.isEmpty())
			this.nombre = nombre;
		else
			throw new RuntimeException("No se ingresó ningún nombre");
	}
	
	public void setOperador(String operador){
		if(!operador.isEmpty())
			this.operador = operador;
		else
			throw new RuntimeException("No se seleccionó ningún operador");
	}
	
	public void setIndicador(String indicador){
		if(!indicador.isEmpty())
			this.indicador = RepositorioIndicadores.instance().buscarIndicador(indicador).get();
		else
			throw new RuntimeException("No se seleccionó ningún indicador");
	}
	
	public void setValue(Double value){
		this.value = value;
	}
	
	public abstract Condicion build();
	
	
}
