package domain.metodologias;


import java.util.ArrayList;
import java.util.List;

import domain.condiciones.*;
import domain.Empresa;

public class AplicaMetodologia {
	
	List<Empresa> empresas;
	
	public AplicaMetodologia(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public List<Empresa> aplicarMetodologia(Metodologia met, String periodo) {
		List<Empresa> listaProcesada = aplicarTaxativas(met.getCondicionesTaxativas(), periodo);
		//listaProcesada = aplicarComparativas(met.getCondicionesComparativas(), listaProcesada);
		return listaProcesada;
	}

	private List<Empresa> aplicarTaxativas(List<CondicionTaxativa> condicionesTaxativas, String periodo) {
		condicionesTaxativas.stream().forEach(cond -> aplicarUnicaTaxativa(cond, periodo));
		return empresas;
	}
	
	private void aplicarUnicaTaxativa(CondicionTaxativa cond, String periodo) {
		empresas = cond.aplicarCondicion(empresas, periodo);
	}

	private List<Empresa> aplicarComparativas(List<CondicionComparativa> condicionesComparativas, List<Empresa> intermedia) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
