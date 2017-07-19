package domain.condiciones;

import org.uqbar.commons.utils.Observable;

import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;
import domain.repositorios.RepositorioIndicadores;
import exceptions.BuilderCondicionesException;

@Observable
public abstract class BuilderCondicion {
	protected String etiquetaBuilder;
	protected String nombre;
	protected OperadorCondicion operador;
	protected Double value;
	protected Indicador indicador;
	
	public BuilderCondicion(String etiquetaBuilder){
		this.etiquetaBuilder = etiquetaBuilder;
	}
	
	public String getEtiquetaBuilder(){
		return etiquetaBuilder;
	}
	
	public  BuilderCondicion setNombre(String nombre){
		if(!nombre.isEmpty())
			this.nombre = nombre;
		else
			throw new BuilderCondicionesException("No se ingresó ningún nombre");
		
		return this;
	}
	
	public BuilderCondicion setOperador(OperadorCondicion operador){
		if(operador != null)
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
