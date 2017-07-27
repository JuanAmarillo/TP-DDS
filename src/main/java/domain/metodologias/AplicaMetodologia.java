package domain.metodologias;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.condiciones.*;
import domain.Empresa;

public class AplicaMetodologia {

	public List<Empresa> aplicar(Metodologia metodologia, List<Empresa> empresas, String periodo) {
		List<EmpresaEnCalculo> empresasEnCalculo = aplicarCondiciones(metodologia.getCondiciones(), empresas, periodo);
		return null;

	}

	private List<EmpresaEnCalculo> aplicarCondiciones(List<Condicion> condiciones, List<Empresa> empresas,
			String periodo) {
		return condiciones.stream().map(condicion -> condicion.apply(empresas, periodo)).flatMap(List::stream)
				.collect(Collectors.toList());
	}


	public List<Empresa> groupByEmpresa(List<EmpresaEnCalculo> empresasEnCalculo, Long cantidadCondiciones) {
		return vecesQuePasoLasCondiciones(empresasEnCalculo).entrySet().stream()
				.filter(empresa -> empresa.getValue() > cantidadCondiciones)
				.map(empresa -> empresa.getKey()).collect(Collectors.toList());

	}

	public Map<Empresa, Long> vecesQuePasoLasCondiciones(List<EmpresaEnCalculo> empresasEnCalculo) {
		return empresasEnCalculo.stream().collect(Collectors.groupingBy(EmpresaEnCalculo::getEmpresa,Collectors.counting()));
	}
}
