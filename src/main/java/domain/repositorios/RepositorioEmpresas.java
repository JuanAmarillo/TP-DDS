package domain.repositorios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import domain.Empresa;

public class RepositorioEmpresas extends Repositorio<Empresa> {

	private static RepositorioEmpresas instance = null;

	public static RepositorioEmpresas instance() {
		if (noHayInstanciaCargada())
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		instance = new RepositorioEmpresas();
	}

	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}

	public static void resetSingleton() {
		instance = null;
	}

	@Override
	protected String getEntityName() {
		return Empresa.class.getSimpleName();
	}

	public void agregarDesdeArchivo(Empresa empresaLeida) {
		try {
			Empresa empresa = findByName(empresaLeida.getNombre()).get();
			empresa.agregarCuentas(empresaLeida.getCuentas());
		} catch (NoSuchElementException e) {
			agregar(empresaLeida);
		}
	}

	public boolean existeLaEmpresa(Empresa empresa) {
		return findByName(empresa.getNombre()).isPresent();
	}

	public Optional<Empresa> findByName(String nombre) {
		return find("nombre", nombre);
	}

	public void deleteByName(String nombreEmpresa) {
		delete("nombre", nombreEmpresa);
	}

	public List<String> getPeriodos() {
		return entityManager.createQuery("select unique periodo from Cuenta").getResultList();// select
																								// periodos
																								// from
																								// cuentas
	}

}