package domain.condiciones;

import org.apache.commons.lang.NotImplementedException;

import domain.Empresa;

public class CondicionTaxativa extends Condicion {

	public Empresa empresa;
	public Double valorDeComparacion;

	public CondicionTaxativa(String nombre) {
		this.nombre = "Taxativa - " + nombre;
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

	@Override
	public Double primerValor(String periodo) {
		return indicador.calcularIndicador(empresa, periodo);
	}

	@Override
	public Double segundoValor(String periodo) {
		return valorDeComparacion;
	}
}
