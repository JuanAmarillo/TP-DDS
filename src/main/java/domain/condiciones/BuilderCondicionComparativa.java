package domain.condiciones;

public class BuilderCondicionComparativa extends BuilderCondicion{

	public BuilderCondicionComparativa() {
		super("Comparativa");
	}

	@Override
	public Condicion build() {
		CondicionComparativa condicion =  new CondicionComparativa(nombre);
		condicion.setOperador(operador);
		condicion.setIndicador(indicador);
		return condicion;
	}

}
