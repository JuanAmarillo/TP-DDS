package domain.condiciones;

public class BuilderCondicionComparativa extends BuilderCondicion{

	@Override
	public Condicion build() {
		CondicionComparativa condicion =  new CondicionComparativa(nombre);
		condicion.setOperador(operador);
		condicion.setIndicador(indicador);
		return condicion;
	}

}
