package ui.vm.cargarMetodologiaVM.factories;

import java.util.List;

import domain.condiciones.Condicion;
import domain.condiciones.CondicionTaxativa;

public class MoverHaciaDerechaTaxativa extends MoverHaciaDerecha<CondicionTaxativa> {
	
	public MoverHaciaDerechaTaxativa(CondicionTaxativa condicion, List<Condicion> condiciones){
		super(condicion,condiciones);
	}

	@Override
	protected void movimiento() {
		condiciones.add(condicion);
	}
}
