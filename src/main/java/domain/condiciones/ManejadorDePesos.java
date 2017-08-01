package domain.condiciones;

import java.util.List;
import java.util.stream.Collectors;

import domain.Empresa;
import domain.metodologias.EmpresaEnCalculo;

public class ManejadorDePesos {
	public Double peso;

	public ManejadorDePesos(Double peso) {
		this.peso = peso;
	}

	public List<EmpresaEnCalculo> agregarPeso(List<Empresa> empresas) {
		return empresas.stream().map(empresa -> agregarPeso(empresa, pesoTotal(empresas, empresa)))
				.collect(Collectors.toList());
	}

	public Double pesoTotal(List<Empresa> empresas, Empresa empresa) {
		return pesoPorPuesto(empresas, empresa) * peso;
	}

	public Integer pesoPorPuesto(List<Empresa> empresas, Empresa empresa) {
		return empresas.size() - empresas.indexOf(empresa);
	}

	public EmpresaEnCalculo agregarPeso(Empresa empresa, Double pesoTotal) {
		return new EmpresaEnCalculo(empresa, pesoTotal);
	}
}