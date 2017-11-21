package batchProccessing;

import domain.Empresa;

public class EmpresaPeriodoARecalcular {

	
	private Empresa empresa;
	private String periodo;
	
	public EmpresaPeriodoARecalcular(Empresa empresa, String periodo) {
		super();
		this.empresa = empresa;
		this.periodo = periodo;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
}
