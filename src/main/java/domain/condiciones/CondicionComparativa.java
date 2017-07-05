package domain.condiciones;

import domain.Empresa;

public class CondicionComparativa extends Condicion {

	private Empresa primerEmpresa;
	private Empresa segundaEmpresa;
	
	public Empresa getPrimerEmpresa() {
		return primerEmpresa;
	}

	public void setPrimerEmpresa(Empresa primerEmpresa) {
		this.primerEmpresa = primerEmpresa;
	}

	public Empresa getSegundaEmpresa() {
		return segundaEmpresa;
	}

	public void setSegundaEmpresa(Empresa segundaEmpresa) {
		this.segundaEmpresa = segundaEmpresa;
	}

	@Override
	public void comparar() {
		// TODO Auto-generated method stub

	}

}
