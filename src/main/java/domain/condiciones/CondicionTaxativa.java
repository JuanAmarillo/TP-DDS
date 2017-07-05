package domain.condiciones;

import domain.Empresa;

public class CondicionTaxativa extends Condicion {

	public Empresa empresa;
	public Double valorDeComparacion;

	@Override
	public void comparar() {
		// TODO Auto-generated method stub
		
	}

	public Empresa getAComparar() {
		return empresa;
	}

	public void setAComparar(Empresa aComparar) {
		this.empresa = aComparar;
	}

	public Double getValorDeComparacion() {
		return valorDeComparacion;
	}

	public void setValorDeComparacion(Double valorDeComparacion) {
		this.valorDeComparacion = valorDeComparacion;
	}
	
}
