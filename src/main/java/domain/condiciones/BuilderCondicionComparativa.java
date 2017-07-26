package domain.condiciones;

public class BuilderCondicionComparativa extends BuilderCondicion{

	public BuilderCondicionComparativa() {
		super("Comparativa");
	}

	@Override
	public Condicion build() {
		return new CondicionComparativa(nombre,indicador,operador);
	}

}
