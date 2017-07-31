package domain.condiciones.buildersCondicion;

import domain.condiciones.Condicion;
import domain.condiciones.CondicionComparativa;

public class BuilderCondicionComparativa extends BuilderCondicion{

	public BuilderCondicionComparativa() {
		super("Comparativa");
	}

	@Override
	public Condicion build() {
		return new CondicionComparativa(nombre,indicador,operador);
	}

}
