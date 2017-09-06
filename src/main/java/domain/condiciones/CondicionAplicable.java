package domain.condiciones;

import org.uqbar.commons.utils.Observable;

@Observable
public class CondicionAplicable {

	Condicion condicion;
	Double peso;
	protected String nombre;

	public CondicionAplicable(Condicion condicion, Double peso) {
		this.condicion = condicion;
		this.peso = peso;
	}

	public CondicionAplicable(CondicionComparativa condicionComparativaSeleccionada, Double pesoDeComparativa) {

		this.condicion = condicionComparativaSeleccionada;
		this.peso = pesoDeComparativa;
	}

	public CondicionAplicable(CondicionTaxativa condicionTaxativaSeleccionada, double peso2) {

		this.condicion = condicionTaxativaSeleccionada;
		this.peso = peso2;
	}

	public Condicion getCondicion() {
		return condicion;
	}

	public void setCondicion(Condicion condicion) {
		this.condicion = condicion;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}