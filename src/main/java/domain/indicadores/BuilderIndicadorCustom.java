package domain.indicadores;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import domain.indicadores.calculoIndicadores.Calculable;
import domain.indicadores.calculoIndicadores.ConstructoresIndicador.Analizador;

public class BuilderIndicadorCustom {
	
	@JsonIgnore
	private String ecuacion;
	@JsonProperty("nombre")
	private String nombre;
	@JsonProperty("expresion")
	private String expresion;
	@JsonIgnore
	private Calculable calculo;
	
	@JsonCreator
	public BuilderIndicadorCustom(@JsonProperty("expresion")String expresion, @JsonProperty("nombre") String nombre) {
		this.nombre = nombre;
		this.expresion = expresion;
	}
	
	public BuilderIndicadorCustom(String ecuacion){
		this.ecuacion = ecuacion;
	}
	
	public IndicadorCustom build() {
		return new IndicadorCustom(nombre, expresion, calculo);
	}
	
	public BuilderIndicadorCustom analizar() {
		new Analizador(ecuacion).parser();
		return this;
	}
	
	public BuilderIndicadorCustom setEcuacion(){
		setNombre(ecuacion);
		setExpresion(ecuacion);
		return this;
	}
	
	private void setNombre(String ecuacion) {
		this.nombre = generarNombre(ecuacion);
	}
	
	public void setExpresion(String ecuacion){
		this.expresion = generarExpresion(ecuacion);
	}
	
	public String generarNombre(String ecuacion) {
		return partesDeEcuacion(ecuacion)[0].trim();
	}
	
	private String generarExpresion(String ecuacion){
		return partesDeEcuacion(ecuacion)[1].trim();
	}
	
	private String[] partesDeEcuacion(String ecuacion) {
		return ecuacion.split("=");
	}
	
	public BuilderIndicadorCustom setCalculo(){
		this.calculo = new Analizador(expresion).compilar();
		return this;
	}
	
}
