package domain.metodologias;

import domain.Empresa;

public class EmpresaEnCalculo {

	private Empresa empresa;
	private Double pesoAcumulado = 0.0;
	
	
	public EmpresaEnCalculo(Empresa empresa) {
		super();
		this.empresa = empresa;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Double getPesoAcumulado() {
		return pesoAcumulado;
	}

	public void setPesoAcumulado(Double pesoAcumulado) {
		this.pesoAcumulado = pesoAcumulado;
	}

	public void agregarPeso(Double peso) {
		this.pesoAcumulado += peso;
	}
	
}
