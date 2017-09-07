package domain.metodologias;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.Empresa;

public class EmpresasAPesar {

	List<Empresa> empresasAPesar;
	Double peso;

	public EmpresasAPesar(List<Empresa> empresasAPesar, Double peso) {
		this.empresasAPesar = empresasAPesar;
		this.peso = peso;
	}

	public List<EmpresaConPeso> darPesoYOrdenar() {
		Stream<EmpresaConPeso> empr = empresasAPesar.stream()
				.map(empresa -> new EmpresaConPeso(empresa, darPeso(empresa)));
		return ordenarPorPeso(empr);
	}

	public List<EmpresaConPeso> ordenarPorPeso(Stream<EmpresaConPeso> empresasNoOrdenadas) {
		return empresasNoOrdenadas.sorted((unaEmpresa, otraEmpresa) -> mayorPeso(unaEmpresa, otraEmpresa))
				.collect(Collectors.toList());
	}

	public int mayorPeso(EmpresaConPeso unaEmpresa, EmpresaConPeso otraEmpresa) {
		return Double.compare(unaEmpresa.getPeso(), otraEmpresa.getPeso());
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