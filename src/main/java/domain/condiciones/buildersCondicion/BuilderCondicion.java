package domain.condiciones.buildersCondicion;

import org.uqbar.commons.utils.Observable;

import java.util.ArrayList;
import java.util.List;
import domain.condiciones.Condicion;
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
		this.nombre = nombre;
		return this;
	}
	
	public BuilderCondicion setOperador(OperadorCondicion operador){
		this.operador = operador;
		return this;
	}
	
	public BuilderCondicion setIndicador(String indicador){		
		this.indicador = RepositorioIndicadores.instance().buscarIndicador(indicador).get();
		return this;
	}
	
	public BuilderCondicion setValue(Double value){
		this.value = value;
		return this;
	}
	
	public Condicion build() {
		validar();
		return buildPosta();
	}
	
	public void validar() {
		List<String> errores = new ArrayList<String>();
		if(nombre == null || nombre.isEmpty())
			errores.add("No se ingresó ningún nombre");
		if(operador == null)
			errores.add("No se seleccionó ningún operador");
		if(indicador == null)	
			errores.add("No se seleccionó ningún indicador");
		if(!errores.isEmpty())
			throw new BuilderCondicionesException(generarMensajeDeError(errores));
	}
	
	public String generarMensajeDeError(List<String> errores) {
		 String mensajito = String.join("\n", errores);
		 return mensajito;
	}

	public abstract Condicion buildPosta();
	public abstract Boolean esTaxativa();
	
	
}
