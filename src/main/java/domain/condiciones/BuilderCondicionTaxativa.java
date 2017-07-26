package domain.condiciones;

public class BuilderCondicionTaxativa extends BuilderCondicion{
	private double valorDeComparacion;	
	
	public BuilderCondicionTaxativa() {
		super("Taxativa");
	}
	
	@Override
	public Condicion build() {
		return new CondicionTaxativa(nombre,indicador,operador,valorDeComparacion);
	}
	
	public void setValorDeComparacion(Double valor){
		this.valorDeComparacion = valor;
	}

}
