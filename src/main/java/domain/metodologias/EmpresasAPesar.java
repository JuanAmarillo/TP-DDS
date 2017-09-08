package domain.metodologias;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.javatuples.Pair;

import domain.Empresa;

public class EmpresasAPesar {

	List<Empresa> empresasAPesar;
	Double peso;

	public EmpresasAPesar(List<Empresa> empresasAPesar, Double peso) {
		this.empresasAPesar = empresasAPesar;
		this.peso = peso;
	}

	public List<Pair<Empresa, Double>> darPeso() {
		List<Pair<Empresa, Double>> empr = empresasAPesar.stream()
				.map(empresa -> Pair.with(empresa, darPeso(empresa))).collect(Collectors.toList());
		return empr;
	}

	public List<Pair<Empresa, Double>> ordenarPorPeso(Stream<Pair<Empresa, Double>> empresasNoOrdenadas) {
		return empresasNoOrdenadas.sorted((unaEmpresa, otraEmpresa) -> mayorPeso(unaEmpresa, otraEmpresa))
				.collect(Collectors.toList());
	}

	public int mayorPeso(Pair<Empresa, Double> unaEmpresa, Pair<Empresa, Double> otraEmpresa) {
		return Double.compare(unaEmpresa.getValue1(), otraEmpresa.getValue1());
	}

	public Double darPeso(Empresa empresa) {
		return (getEmpresasAPesar().size() - getEmpresasAPesar().indexOf(empresa)) * this.getPeso();
	}

	// GETTERS Y SETTERS

	public Empresa getEmpresaEnPosicion(int i) {
		return this.getEmpresasAPesar().get(i);
	}

	public List<Empresa> getEmpresasAPesar() {
		return empresasAPesar;
	}

	public void setEmpresasAPesar(List<Empresa> empresasAPesar) {
		this.empresasAPesar = empresasAPesar;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

}