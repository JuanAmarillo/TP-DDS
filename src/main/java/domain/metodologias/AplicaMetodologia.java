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
		aplicarTaxativas(met.getCondicionesTaxativas(), periodo);
		aplicarComparativas(met.getCondicionesComparativas(), periodo);
		return empresas;
	}

	private void aplicarTaxativas(List<CondicionTaxativa> condicionesTaxativas, String periodo) {
		condicionesTaxativas.stream().forEach(cond -> aplicarUnicaTaxativa(cond, periodo));
	}
	
	private void aplicarUnicaTaxativa(CondicionTaxativa cond, String periodo) {
		empresas = cond.aplicarCondicion(empresas, periodo);
	}

	private List<Empresa> aplicarComparativas(List<CondicionComparativa> condicionesComparativas, String periodo) {
		
		return empresas;
	}

	
	
}
