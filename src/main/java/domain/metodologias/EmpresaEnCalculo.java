package domain.metodologias;

import domain.Empresa;

public class EmpresaEnCalculo {

	private Empresa empresa;
	private Double peso = 0.0;
	
	
	public EmpresaEnCalculo(Empresa empresa,Double peso) {
		this.empresa = empresa;
		this.peso = peso;
	}
	
	public EmpresaEnCalculo(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double pesoAcumulado) {
		this.peso = pesoAcumulado;
	}
	
}
