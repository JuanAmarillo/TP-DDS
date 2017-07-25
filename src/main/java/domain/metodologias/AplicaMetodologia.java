package domain.metodologias;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.condiciones.*;
import domain.Empresa;

public class AplicaMetodologia {
	
	List<EmpresaEnCalculo> listaEmpresas = new ArrayList<EmpresaEnCalculo>();
	
	public AplicaMetodologia(List<Empresa> empresas) {
		listaEmpresas = generarEmpresasEnCalculo(empresas);
	}

	private List<EmpresaEnCalculo> generarEmpresasEnCalculo(List<Empresa> empresas) {
		return empresas.stream().map(empresa -> new EmpresaEnCalculo(empresa)).collect(Collectors.toList());
	}

	public List<Empresa> aplicarMetodologia(Metodologia met, String periodo) {
		aplicarTaxativas(met.getCondicionesTaxativas(), periodo);
		aplicarComparativas(met.getCondicionesComparativas(), periodo);
		ordenarLista();
		return obtenerEmpresas();
	}

	private void ordenarLista() {
		listaEmpresas = listaEmpresas.stream()
					   .sorted((e1,e2) -> (int) compararValores(e1,e2))
					   .collect(Collectors.toList());
	}

	private int compararValores(EmpresaEnCalculo e1, EmpresaEnCalculo e2) {
		return Double.compare(e1.getPesoAcumulado(),e2.getPesoAcumulado());
	}


	private void aplicarTaxativas(List<CondicionTaxativa> condicionesTaxativas, String periodo) {
		condicionesTaxativas.stream().forEach(cond -> aplicarUnicaTaxativa(cond, periodo));
	}
	
	private void aplicarUnicaTaxativa(CondicionTaxativa cond, String periodo) {
		actualizarLista(cond.aplicarCondicion(obtenerEmpresas(), periodo));
	}

	private void actualizarLista(List<Empresa> listaFiltrada) {
		List<EmpresaEnCalculo> empresasEnCalculo = generarEmpresasEnCalculo(listaFiltrada);
		listaEmpresas = listaEmpresas.stream()
					 .filter(empresa -> contieneALaEmpresa(empresa.getEmpresa(), empresasEnCalculo)).collect(Collectors.toList());
	}

	private boolean contieneALaEmpresa(Empresa empresa, List<EmpresaEnCalculo> empresasEnCalculo) {
		return empresasEnCalculo.stream().map(empresaMap -> empresaMap.getEmpresa()).anyMatch(empresaFind -> empresaFind.esLaMismaEmpresaQue(empresa));
	}

	private List<Empresa> obtenerEmpresas() {
		return listaEmpresas.stream().map(empresaEnCalculo -> empresaEnCalculo.getEmpresa()).collect(Collectors.toList());
	}

	private void aplicarComparativas(List<CondicionComparativa> condicionesComparativas, String periodo) {
		condicionesComparativas.stream().forEach(cond -> aplicarUnicaComparativa(cond,periodo));
	}

	private void aplicarUnicaComparativa(CondicionComparativa cond, String periodo) {
		List<Empresa> listaComparada = cond.aplicarCondicion(obtenerEmpresas(), periodo);
		agregarPesos(listaComparada, cond.getPeso());
	}

	private void agregarPesos(List<Empresa> listaComparada, Double pesoDeLaCondicion) {
		listaComparada.stream().forEach(empresa -> sumarPeso(getPosicionDeLaEmpresa(listaComparada,empresa), pesoDeLaCondicion, empresa));
	}

	private void sumarPeso(double multiplicador, Double pesoDeLaCondicion, Empresa empresa) {
		listaEmpresas.stream().filter(emp -> emp.getEmpresa().esLaMismaEmpresaQue(empresa)).findFirst().get().agregarPeso(pesoDeLaCondicion*multiplicador);;	
	}

	private double getPosicionDeLaEmpresa(List<Empresa> listaComparada, Empresa empresa) {
		return (double) listaComparada.indexOf(empresa);
	}
}
