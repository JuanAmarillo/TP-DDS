package ui.vm;

import java.util.List;

import org.uqbar.commons.utils.Observable;

@Observable
public class ListadoDatosCuentasVM<T, E> {
	public List<T> elementos;
	public T elementoSeleccionado;
	public List<E> periodos;
	public E periodoSeleccionado;

	public List<T> getElementos() {
		return elementos;
	}

	public void setElementos(List<T> elementos) {
		this.elementos = elementos;
	}

	public T getElementoSeleccionado() {
		return elementoSeleccionado;
	}

	public void setElementoSeleccionado(T elementoSeleccionado) {
		this.elementoSeleccionado = elementoSeleccionado;
	}

	public List<E> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<E> periodos) {
		this.periodos = periodos;
	}

	public E getPeriodoSeleccionado() {
		return periodoSeleccionado;
	}

	public void setPeriodoSeleccionado(E periodoSeleccionado) {
		this.periodoSeleccionado = periodoSeleccionado;
	}

}
