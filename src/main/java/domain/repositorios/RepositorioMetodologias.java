package domain.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import domain.metodologias.Metodologia;
import exceptions.YaExisteLaMetodologiaException;

public class RepositorioMetodologias {
	private static RepositorioMetodologias instance=null;
	private List<Metodologia> metodologiasCargadas = new ArrayList<Metodologia>();
	
	public static RepositorioMetodologias instance() {
		if (noHayInstanciaCargada()) 
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		instance = new RepositorioMetodologias();
	}

	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}

	public static void resetSingleton() {
		instance = null;
	}
	
	public Optional<Metodologia> buscarMetodologia(String nombre) {
		return metodologiasCargadas.stream().filter(metodologia -> metodologia.suNombreEs(nombre)).findFirst();
	}
	
	public void agregarMetodologia(Metodologia metodologia) {
		verificarSiExiste(metodologia);
		add(metodologia);
	}

	public void add(Metodologia metodologia) {
		metodologiasCargadas.add(metodologia);
	}

	public List<Metodologia> getMetodologiasCargadas() {
		return metodologiasCargadas;
	}
	
	public void verificarSiExiste(Metodologia metodologia){
		if(existeLaMetodologia(metodologia))
			throw new YaExisteLaMetodologiaException();
	}

	public boolean existeLaMetodologia(Metodologia metodologia) {
		return buscarMetodologia(metodologia.getNombre()).isPresent();
	}

	public List<String> getNombresMetodologias() {
		return getMetodologiasCargadas().stream().map(met -> met.getNombre()).collect(Collectors.toList());
	}
}
