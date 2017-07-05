package domain.condiciones;

import org.apache.commons.lang.NotImplementedException;

import domain.Empresa;

public class CondicionComparativa extends Condicion {

	private Empresa primerEmpresa;
	private Empresa segundaEmpresa;
	
	public Empresa getPrimerEmpresa() {
		return primerEmpresa;
	}

	public CondicionComparativa setPrimerEmpresa(Empresa primerEmpresa) {
		this.primerEmpresa = primerEmpresa;
		return this;
	}

	public Empresa getSegundaEmpresa() {
		return segundaEmpresa;
	}

	public CondicionComparativa setSegundaEmpresa(Empresa segundaEmpresa) {
		this.segundaEmpresa = segundaEmpresa;
		return this;
	}

	@Override
	public boolean comparar() {
		throw new NotImplementedException();
	}

}
