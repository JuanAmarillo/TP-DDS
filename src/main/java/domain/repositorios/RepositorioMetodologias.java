package domain.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import domain.condiciones.Condicion;
import domain.metodologias.Metodologia;

public class RepositorioMetodologias {
	private static List<Metodologia> metodologiasCargadas;
	private static RepositorioMetodologias instance=null;
	
	public static RepositorioMetodologias instance() {
		if (noHayInstanciaCargada()) 
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		metodologiasCargadas = new ArrayList<Metodologia>();
		instance = new RepositorioMetodologias();
	}

	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}

	public static void resetSingleton() {
		instance = null;
	}
	
	public List<Metodologia> getCondicionesCargadas() {
		List<Metodologia> mets = metodologiasCargadas;
		return mets;
	}
	
	public Optional<Metodologia> buscarMetodologia(String nombre) {
		return metodologiasCargadas.stream().filter(metodologia -> metodologia.suNombreEs(nombre)).findFirst();
	}
	
	public void agregarMetodologia(Metodologia metodologia) {
		getMetodologiasCargadas().add(metodologia);
	}

	public List<Metodologia> getMetodologiasCargadas() {
		return metodologiasCargadas;
	}

	public List<String> getNombresMetodologias() {
		return getMetodologiasCargadas().stream().map(met -> met.getNombre()).collect(Collectors.toList());
	}
}
