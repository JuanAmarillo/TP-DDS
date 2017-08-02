package ui.vm.cargarMetodologiaVM.factories;

import java.util.List;

import domain.condiciones.Condicion;
import domain.condiciones.CondicionTaxativa;

public class AgregarCondicionTaxativa extends AgregarCondicion<CondicionTaxativa> {
	
	public AgregarCondicionTaxativa(CondicionTaxativa condicion, List<Condicion> condiciones){
		super(condicion,condiciones);
	}

	@Override
	protected void movimiento() {
		condiciones.add(condicion);
	}
}
