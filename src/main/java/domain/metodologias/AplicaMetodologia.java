package domain.metodologias;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import domain.condiciones.*;
import domain.Empresa;

public class AplicaMetodologia {

	public List<Empresa> aplicar(Metodologia metodologia, List<Empresa> empresas, String periodo) {
		Map<Empresa, Double> empresasAplicadas = empresasAplicadas(metodologia.getCondiciones(),empresas, periodo);
		return obtenerEmpresasPorMayorPeso(empresasAplicadas);
	}

	private List<Empresa> obtenerEmpresasPorMayorPeso(Map<Empresa, Double> empresasAplicadas) {
		return empresasAplicadas.entrySet().stream()
				.sorted((unaEmpresa, otraEmpresa) -> mayorPeso(unaEmpresa, otraEmpresa))
				.map(empresas -> empresas.getKey()).collect(Collectors.toList());
	}

	public int mayorPeso(Entry<Empresa, Double> e1, Entry<Empresa, Double> e2) {
		return Double.compare(e2.getValue(), e1.getValue());
	}

	private Map<Empresa, Double> empresasAplicadas(List<Condicion> condiciones, List<Empresa> empresas,
			String periodo) {
		return aplicarCondiciones(condiciones, empresas, periodo).entrySet().stream()
				.filter(empresasCondicionadas -> pasoCondiciones(condiciones, empresasCondicionadas))
				.map(empresasCondicionadas -> empresasCondicionadas.getValue()).flatMap(List::stream)
				.collect(agruparPorEmpresaYSumarPesos());
	}

	private Map<Empresa, List<EmpresaEnCalculo>> aplicarCondiciones(List<Condicion> condiciones, List<Empresa> empresas,
			String periodo) {
		return condiciones.stream().map(condicion -> condicion.apply(empresas, periodo)).flatMap(List::stream)
				.collect(Collectors.groupingBy(EmpresaEnCalculo::getEmpresa));
	}

	public Collector<EmpresaEnCalculo, ?, Map<Empresa, Double>> agruparPorEmpresaYSumarPesos() {
		return Collectors.groupingBy(EmpresaEnCalculo::getEmpresa, Collectors.summingDouble(EmpresaEnCalculo::getPeso));
	}

	public boolean pasoCondiciones(List<Condicion> condiciones, Entry<Empresa, List<EmpresaEnCalculo>> empresas) {
		return empresas.getValue().size() == condiciones.size();
	}

}
