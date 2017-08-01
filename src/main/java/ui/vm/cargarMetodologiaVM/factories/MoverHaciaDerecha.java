package ui.vm.cargarMetodologiaVM.factories;

import java.util.List;

import domain.condiciones.Condicion;

public abstract class MoverHaciaDerecha<T extends Condicion> {
	
	protected T condicion;
	protected List<Condicion> condiciones;
	
	public MoverHaciaDerecha(T condicion, List<Condicion> condiciones){
		this.condicion = condicion;
		this.condiciones = condiciones;
	}
	
	public void mover(){
		validaciones();
		movimiento();
	}
	
	protected void validaciones(){
		if (condicion == null)
			throw new RuntimeException("Seleccione una condicion");
		if (seAgregoAnteriormente())
			throw new RuntimeException("Esa condicion ya fue agregada");
	}
	
	protected boolean seAgregoAnteriormente() {
		return condiciones.contains(condicion);
	}
	
	protected abstract void movimiento();
	
}
