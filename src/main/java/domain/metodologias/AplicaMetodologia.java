package domain.metodologias;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		List<Empresa> listaFinal = ordenarLista();
		limpiarPesos();
		return listaFinal;
	}

	private List<Empresa> ordenarLista() {
		return empresas.stream()
					   .sorted((e1,e2) -> (int) compararValores(e1,e2))
					   .collect(Collectors.toList());
	}

	private int compararValores(Empresa e1, Empresa e2) {
		return Double.compare(e1.getPeso(),e2.getPeso());
	}

	private void limpiarPesos() {
		empresas.stream().forEach(empresa -> empresa.resetPeso());
	}

	private void aplicarTaxativas(List<CondicionTaxativa> condicionesTaxativas, String periodo) {
		condicionesTaxativas.stream().forEach(cond -> aplicarUnicaTaxativa(cond, periodo));
	}
	
	private void aplicarUnicaTaxativa(CondicionTaxativa cond, String periodo) {
		empresas = cond.aplicarCondicion(empresas, periodo);
	}

	private void aplicarComparativas(List<CondicionComparativa> condicionesComparativas, String periodo) {
		condicionesComparativas.stream().forEach(cond -> aplicarUnicaComparativa(cond,periodo));
	}

	private void aplicarUnicaComparativa(CondicionComparativa cond, String periodo) {
		List<Empresa> listaComparada = cond.aplicarCondicion(empresas, periodo);
		agregarPesos(listaComparada, cond.getPeso());
	}

	private void agregarPesos(List<Empresa> listaComparada, Double pesoDeLaCondicion) {
		listaComparada.stream().forEach(empresa -> sumarPeso(listaComparada, pesoDeLaCondicion, empresa));
	}

	private void sumarPeso(List<Empresa> listaComparada, Double pesoDeLaCondicion, Empresa empresa) {
		empresa.sumarPeso(pesoDeLaCondicion * getPosicionDeLaEmpresa(listaComparada, empresa));
	}

	private double getPosicionDeLaEmpresa(List<Empresa> listaComparada, Empresa empresa) {
		return (double) listaComparada.indexOf(empresa);
	}

	
	
}
