package domain.condiciones;

public class BuilderCondicionTaxativa extends BuilderCondicion{
	private double valorDeComparacion;
	
	@Override
	public Condicion build() {
		CondicionTaxativa condicion = new CondicionTaxativa(nombre);
		condicion.setOperador(operador);
		condicion.setIndicador(indicador);
		condicion.setValorDeComparacion(valorDeComparacion);
		return condicion;
	}
	
	public void setValorDeComparacion(Double valor){
		this.valorDeComparacion = valor;
	}

}
