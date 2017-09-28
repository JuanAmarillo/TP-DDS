package domain.repositorios;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.HibernateException;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import exceptions.NoSePudoCargarAlRepositorioException;

public abstract class Repositorio<T> {

	protected EntityManager entityManager = PerThreadEntityManagers.getEntityManager();

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@SuppressWarnings("unchecked")
	public List<T> obtenerLista(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	public List<T> getElementos() {
		return obtenerLista(from());
	}
	
	public List<String> getNombres() {
		return entityManager.createQuery("SELECT i.nombre FROM "+ getEntityName() +" i", String.class).getResultList();
	}

	public void agregar(T elemento) {
		persistir(elemento);
	}

	public void crearTransaccion() {
		EntityTransaction tx = entityManager.getTransaction();
		if (!tx.isActive())
			tx.begin();
	}
	
	public void cerrarTransaccion(String entidad){
		EntityTransaction tx = entityManager.getTransaction();
		try {
			if (!tx.isActive())
				tx.commit();
		}
		catch(HibernateException e) {
			tx.rollback();
			throw new NoSePudoCargarAlRepositorioException("No se pudo cargar " + entidad + " correctamente. Intente mas tarde.");
		}
	}

	protected void persistir(Object elemento) {
		entityManager.persist(elemento);
	}

	public Boolean hayElementosCargados() {
		return cantidadElementosCargados() > 0;
	}

	public Long cantidadElementosCargados() {
		return (Long) entityManager.createQuery("select count(*)" + from()).getSingleResult();
	}

	public void resetEntityManager() {
		entityManager.flush();
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

	private String from() {
		return "from " + getEntityName() + " ";
	}

	private String where(String donde, String elemento) {
		return "where " + donde + "= '" + elemento + "'";
	}

	public void deleteById(Integer id) {
		delete("id", id.toString());
	}

	public void deleteByName(String nombreEmpresa) {
		delete("nombre", nombreEmpresa);
	}

	protected void delete(String donde, String elemento) {
		// EntityTransaction tx = crearTransaccion();
		entityManager.createQuery("delete from " + getEntityName() + " " + where(donde, elemento)).executeUpdate();
		// tx.commit();
	}

	abstract protected String getEntityName();
	// abstract protected String getId(T elemento);

}
