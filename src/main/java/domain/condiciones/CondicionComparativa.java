package domain.condiciones;

import domain.Empresa;
import net.sf.oval.exception.MethodNotFoundException;

public class CondicionComparativa extends Condicion {

	private Empresa primerEmpresa;
	private Empresa segundaEmpresa;
	
	public CondicionComparativa(String nombre){
		this.nombre = "Comparativa - " + nombre;
	}
	
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
		throw new MethodNotFoundException("Comparacion de Indicadores");
	}

}
