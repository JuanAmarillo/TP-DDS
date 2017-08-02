package ui.vm.cargarMetodologiaVM.factories;

import java.util.List;

import domain.condiciones.Condicion;

public abstract class AgregarCondicion<T extends Condicion> {
	
	protected T condicion;
	protected List<Condicion> condiciones;
	
	public AgregarCondicion(T condicion, List<Condicion> condiciones){
		this.condicion = condicion;
		this.condiciones = condiciones;
	}
	
	public void agregar(){
		validaciones();
		maneraDeAgregar();
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
	
	protected abstract void maneraDeAgregar();
	
}
