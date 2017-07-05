package domain.condiciones;

import org.apache.commons.lang.NotImplementedException;

import domain.Empresa;

public class CondicionTaxativa extends Condicion {

	public Empresa empresa;
	public Double valorDeComparacion;

	@Override
	public boolean comparar() {
		throw new NotImplementedException();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public CondicionTaxativa setEmpresa(Empresa aComparar) {
		this.empresa = aComparar;
		return this;
	}

	public Double getValorDeComparacion() {
		return valorDeComparacion;
	}

	public void setValorDeComparacion(Double valorDeComparacion) {
		this.valorDeComparacion = valorDeComparacion;
	}
}
