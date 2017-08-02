package ui.vm.cargarMetodologiaVM.factories;

import java.util.List;

import domain.condiciones.Condicion;
import domain.condiciones.CondicionComparativa;
import domain.condiciones.ManejadorDePesos;

public class AgregarCondicionComparativa extends AgregarCondicion<CondicionComparativa> {

	private Double peso;

	public AgregarCondicionComparativa(CondicionComparativa condicion, List<Condicion> condiciones, Double peso) {
		super(condicion, condiciones);
		this.peso = peso;
	}

	@Override
	public void maneraDeAgregar() {
		condicion.setManejadorDePesos(new ManejadorDePesos(peso));
		condiciones.add(condicion);
	}

	protected void validaciones() {
		super.validaciones();
		pesoInvalido();
	}

	private void pesoInvalido() {
		if (peso <= 0)
			throw new RuntimeException("Debe agregar un peso para la condicion");
	}

}
