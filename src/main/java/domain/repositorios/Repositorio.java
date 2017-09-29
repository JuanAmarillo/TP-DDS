package domain.repositorios;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import exceptions.NoSePudoCargarAlRepositorioException;

public abstract class Repositorio<T> {

	// ver el caso de agregar y tira excepcion si ya existe
	
	// ver si se puede crear una clase para arreglar los problemas de pedir id o
	// nombre (deletebyid repoIndicadores=

	protected EntityManager entityManager = PerThreadEntityManagers.getEntityManager();

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void crearTransaccion() {
		EntityTransaction tx = entityManager.getTransaction();
		if (!tx.isActive())
			tx.begin();
	}

	public void cerrarTransaccion(String entidad) {
		EntityTransaction tx = entityManager.getTransaction();
		try {
			if (!tx.isActive())
				tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			throw new NoSePudoCargarAlRepositorioException(
					"No se pudo cargar " + entidad + " correctamente. Intente mas tarde.");
		}
	}

	public void resetEntityManager() {
		entityManager.flush();
	}

	protected void persistir(Object elemento) {
		entityManager.persist(elemento);
	}

	@SuppressWarnings("unchecked")
	public List<T> obtenerLista(String query) {
		return createQuery(query).getResultList();
	}

	protected Query createQuery(String query) {
		return entityManager.createQuery(query);
	}

	public List<T> getElementos() {
		return obtenerLista(from());
	}

	public <G> List<G> getElementosDe(Class<G> clase) {
		return entityManager.createQuery("from " + clase.getSimpleName(), clase).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<String> getNombres() {
		return createQuery("select nombre" + from()).getResultList();
	}

	public void agregar(T elemento) {
		persistir(elemento);
	}

	public Boolean hayElementosCargados() {
		return cantidadElementosCargados() > 0;
	}

	public Long cantidadElementosCargados() {
		return (Long) createQuery("select count(*)" + from()).getSingleResult();
	}

	public Optional<T> findById(Integer id) {
		return find("id", id.toString());
	}

	public Optional<T> findByName(String nombre) {
		return find("nombre", nombre);
	}

	protected Optional<T> find(String donde, String elemento) {
		return obtenerLista(from() + where(donde, elemento)).stream().findFirst();
	}

	public boolean verificarExistencia(String nombre) {
		return findByName(nombre).isPresent();
	}

	public void deleteById(Integer id) {
		delete("id", id.toString());
	}

	public void deleteByName(String nombreEmpresa) {
		delete("nombre", nombreEmpresa);
	}

	protected void delete(String donde, String elemento) {
		createQuery("delete" + from() + where(donde, elemento)).executeUpdate();
	}

	private String from() {
		return " from " + getEntityName() + " ";
	}

	private String where(String donde, String elemento) {
		return " where " + donde + "= '" + elemento + "'";
	}

	abstract protected String getEntityName();
	// abstract protected String getId(T elemento);

}
