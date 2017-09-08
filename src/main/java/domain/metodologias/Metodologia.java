package domain.metodologias;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.javatuples.Pair;
import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.condiciones.Condicion;
import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionTaxativa;

@Observable
public class Metodologia {

	private String nombre;
	private List<Condicion> condiciones;

	public Metodologia(String nombre, List<Condicion> condiciones) {
		this.nombre = nombre;
		this.condiciones = condiciones;
	}

	public List<Empresa> aplicarCondiciones(List<Empresa> empresas/*, String periodo*/) {
		List<Empresa> emprFiltradas = this.aplicarCondicionesTaxativas(empresas/*, periodo*/);
		return aplicarCondicionesComparativas(emprFiltradas/*, periodo*/);

	}

	public List<Empresa> aplicarCondicionesTaxativas(List<Empresa> empresas /*, String periodo*/) {
		List<Condicion> condicionesTaxativas = this.obtenerCondicionesTaxativas();
		if (condicionesTaxativas.isEmpty()) {
			return empresas;
		} else {
			List<Empresa> emprFiltradas = condicionesTaxativas.stream()
					.map(condicion -> condicion.aplicarCondicion(empresas)).flatMap(List::stream)
					.collect(Collectors.toList());
			return emprFiltradas.stream().filter(empresa -> Collections.frequency(emprFiltradas, empresa) == this
					.obtenerCondicionesTaxativas().size()).collect(Collectors.toList());
		}
	}

	public List<Empresa> aplicarCondicionesComparativas(List<Empresa> empresas /*, String periodo*/) {
		List<CondicionComparativa> condicionesComparativas = this.obtenerCondicionesComparativas();
		if (condicionesComparativas.isEmpty()) {
			return empresas;
		} else {
			List<EmpresasAPesar> empresasSinPeso = condicionesComparativas.stream()
					.map(condicionComparativa -> new EmpresasAPesar(
							condicionComparativa.aplicarCondicion(empresas), condicionComparativa.getPeso()))
					.collect(Collectors.toList());
			// obtiene diccionario de empresas con su peso
			Map<Empresa, Double> empresasOrdenadas = sortByPeso(darPesoALasEmpresas(empresasSinPeso));
			// pasa todo a List<Empresa>3¡?
			return pasarMapAList(empresasOrdenadas);
		}
	}

	private Map<Empresa, Double> darPesoALasEmpresas(List<EmpresasAPesar> empresasSinPeso) {
		return empresasSinPeso.stream().map(e -> e.darPeso())
				.flatMap(List::stream).collect(Collectors.groupingBy(Pair<Empresa, Double>::getValue0,
						Collectors.summingDouble(Pair<Empresa, Double>::getValue1)));
	}

	public List<Empresa> pasarMapAList(Map<Empresa, Double> map) {
		return map.entrySet().stream().map(empresa -> empresa.getKey()).collect(Collectors.toList());
	}

	public Map<Empresa, Double> sortByPeso(Map<Empresa, Double> map) {
		return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	public List<Condicion> obtenerCondicionesTaxativas() {
		List<Condicion> condicionesTFiltradas = this.getCondiciones().stream()
				.filter(cond -> cond.esTaxativa()).collect(Collectors.toList());
		return condicionesTFiltradas;
	}

	public List<CondicionComparativa> obtenerCondicionesComparativas() {
		List<CondicionComparativa> condicionesCFiltradas = this.getCondiciones().stream()
				.filter(cond -> !cond.esTaxativa())
				.map(cond -> (CondicionComparativa) cond).collect(Collectors.toList());
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

	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}

}