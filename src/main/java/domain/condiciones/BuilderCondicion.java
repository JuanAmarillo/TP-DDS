package domain.condiciones;

import domain.indicadores.Indicador;
import domain.repositorios.RepositorioIndicadores;
import exceptions.BuilderCondicionesException;

public abstract class BuilderCondicion {
	protected String nombre;
	protected String operador;
	protected Double value;
	protected Indicador indicador;
	
	public  BuilderCondicion setNombre(String nombre){
		if(!nombre.isEmpty())
			this.nombre = nombre;
		else
			throw new BuilderCondicionesException("No se ingresó ningún nombre");
		
		return this;
	}
	
	public BuilderCondicion setOperador(String operador){
		if(!operador.isEmpty())
			this.operador = operador;
		else
			throw new BuilderCondicionesException("No se seleccionó ningún operador");
		
		return this;
	}
	
	public BuilderCondicion setIndicador(String indicador){
		if(!indicador.isEmpty())
			this.indicador = RepositorioIndicadores.instance().buscarIndicador(indicador).get();
		else
			throw new BuilderCondicionesException("No se seleccionó ningún indicador");
		
		return this;
	}
	
	public BuilderCondicion setValue(Double value){
		this.value = value;
		return this;
	}
	
	public abstract Condicion build();
	
	
}
