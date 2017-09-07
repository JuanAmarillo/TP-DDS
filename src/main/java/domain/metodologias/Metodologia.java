package domain.metodologias;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.javatuples.Pair;
import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.condiciones.CondicionAplicable;

@Observable
public class Metodologia {

	private String nombre;
	private List<CondicionAplicable> condiciones;

	public Metodologia(String nombre, List<CondicionAplicable> condiciones) {
		this.nombre = nombre;
		this.condiciones = condiciones;
	}

	public List<Empresa> aplicarCondiciones(List<Empresa> empresas/*, String periodo*/) {
		List<Empresa> emprFiltradas = this.aplicarCondicionesTaxativas(empresas/*, periodo*/);
		return aplicarCondicionesComparativas(emprFiltradas/*, periodo*/);

	}

	public List<Empresa> aplicarCondicionesTaxativas(List<Empresa> empresas /*, String periodo*/) {
		List<CondicionAplicable> condicionesTaxativas = this.obtenerCondicionesTaxativas();
		if (condicionesTaxativas.isEmpty()) {
			return empresas;
		} else {
			List<Empresa> emprFiltradas = condicionesTaxativas.stream()
					.map(condicion -> condicion.getCondicion().aplicarCondicion(empresas)).flatMap(List::stream)
					.collect(Collectors.toList());
			return emprFiltradas.stream().filter(empresa -> Collections.frequency(emprFiltradas, empresa) == this
					.obtenerCondicionesTaxativas().size()).collect(Collectors.toList());
		}
	}

	public List<Empresa> aplicarCondicionesComparativas(List<Empresa> empresas /*, String periodo*/) {
		List<CondicionAplicable> condicionesComparativas = this.obtenerCondicionesComparativas();
		if (condicionesComparativas.isEmpty()) {
			return empresas;
		} else {
			List<EmpresasAPesar> empresasSinPeso = condicionesComparativas.stream()
					.map(condicionAplicable -> new EmpresasAPesar(
							condicionAplicable.getCondicion().aplicarCondicion(empresas), condicionAplicable.getPeso()))
					.collect(Collectors.toList());
			// obtiene diccionario de empresas con su peso
			Map<Empresa, Double> empresasConPeso = empresasSinPeso.stream().map(e -> e.darPesoYOrdenar())
					.flatMap(List::stream).collect(Collectors.groupingBy(Pair<Empresa, Double>::getValue0,
							Collectors.summingDouble(Pair<Empresa, Double>::getValue1)));
			// ordena por peso
			Map<Empresa, Double> empresasConPesoOrdenadas = sortByPeso(empresasConPeso);
			// pasa todo a List<Empresa>
			return pasarMapAList(empresasConPesoOrdenadas);

		}
	}

	public List<Empresa> pasarMapAList(Map<Empresa, Double> map) {
		return map.entrySet().stream().map(empresa -> empresa.getKey()).collect(Collectors.toList());
	}

	public Map<Empresa, Double> sortByPeso(Map<Empresa, Double> map) {
		return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	public List<CondicionAplicable> obtenerCondicionesTaxativas() {
		List<CondicionAplicable> condicionesTFiltradas = this.getCondiciones().stream()
				.filter(cond -> cond.getCondicion().esTaxativa()).collect(Collectors.toList());
		return condicionesTFiltradas;
	}

	public List<CondicionAplicable> obtenerCondicionesComparativas() {
		List<CondicionAplicable> condicionesCFiltradas = this.getCondiciones().stream()
				.filter(cond -> !cond.getCondicion().esTaxativa()).collect(Collectors.toList());
		return condicionesCFiltradas;
	}

	// GETTERS Y SETTERS//
	public boolean suNombreEs(String nombre) {
		return this.nombre.equals(nombre);
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public List<CondicionAplicable> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<CondicionAplicable> condiciones) {
		this.condiciones = condiciones;
	}

}