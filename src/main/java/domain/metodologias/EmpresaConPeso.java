package domain.metodologias;

import domain.Empresa;

public class EmpresaConPeso {

	Empresa empresa;
	Double peso;
	
	public EmpresaConPeso(Empresa empresa, Double peso){
		this.empresa=empresa;
		this.peso=peso;
	}

	//GETTERS Y SETTERS
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	
	
}
