package domain.condiciones.buildersCondicion;

import domain.condiciones.Condicion;
import domain.condiciones.CondicionTaxativa;

public class BuilderCondicionTaxativa extends BuilderCondicion{
	private double valorDeComparacion;	
	
	public BuilderCondicionTaxativa() {
		super("Taxativa");
	}
	
	@Override
	public Condicion buildPosta() {
		return new CondicionTaxativa(nombre,indicador,operador,valorDeComparacion);
	}
	
	public void setValorDeComparacion(Double valor){
		this.valorDeComparacion = valor;
	}

	@Override
	public Boolean esTaxativa() {
		return true;
	}

}