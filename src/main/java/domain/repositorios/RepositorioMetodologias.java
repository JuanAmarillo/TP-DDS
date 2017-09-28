package domain.repositorios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.HibernateException;

import domain.metodologias.Metodologia;
import exceptions.YaExisteLaMetodologiaException;

public class RepositorioMetodologias extends Repositorio<Metodologia> {

	private static RepositorioMetodologias instance = null;

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
		return find("nombre", nombre);
	}

	public void agregarMetodologia(Metodologia metodologia) {
		agregar(metodologia);
	}

	public List<Metodologia> getMetodologiasCargadas() {
		return entityManager.createQuery("SELECT i FROM Metodologia i", Metodologia.class).getResultList();
	}
	public List<String> getNombresMetodologias() {
		return getMetodologiasCargadas().stream().map(met -> met.getNombre()).collect(Collectors.toList());
	}

	@Override
	protected String getEntityName() {
		return Metodologia.class.getSimpleName();
	}
}
