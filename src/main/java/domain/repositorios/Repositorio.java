package domain.repositorios;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.TransactionManager;

public abstract class Repositorio<T> {

	// ver el caso de agregar y tira excepcion si ya existe
	
	// ver si se puede crear una clase para arreglar los problemas de pedir id o
	// nombre (deletebyid repoIndicadores=

	public EntityManager getEntityManager() {
		return TransactionManager.instance().getEntityManager();
	}
	
	
	protected void persistir(Object elemento) {
		getEntityManager().persist(elemento);
	}
	
	@SuppressWarnings("unchecked")
	public <G> List<G> obtenerLista(String query,Class<G> clase) {
		return createQuery(query,clase).getResultList();
	}

	protected <G> Query createQuery(String query,Class<G> clase) {
		return getEntityManager().createQuery(query,clase);
	}

	
	protected <G> Query createQuery(String query) {
		return getEntityManager().createQuery(query);
	}

	public List<T> getElementos() {
		return getElementosDe(getEntity());
	}

	public <G> List<G> getElementosDe(Class<G> clase) {
		return obtenerLista("from " + clase.getSimpleName(), clase);
	}

	public List<String> getNombres() {
		return obtenerLista("select nombre" + from(),String.class);
	}

	public void agregar(T elemento) {
		persistir(elemento);
	}

	public Boolean hayElementosCargados() {
		return cantidadElementosCargados() > 0;
	}

	public Long cantidadElementosCargados() {
		return (Long) createQuery("select count(*)" + from(),Long.class).getSingleResult();
	}

	public Optional<T> findById(Integer id) {
		return find("id", id.toString());
	}

	public Optional<T> findByName(String nombre) {
		return find("nombre", nombre);
	}

	public Optional<T> find(String donde, String elemento) {
		return obtenerLista(from() + where(donde, elemento),getEntity()).stream().findFirst();
	}

	public boolean verificarExistencia(String nombre) {
		return findByName(nombre).isPresent();
	}

	public void deleteById(Integer id) {
		delete("id", id.toString());
	}

	public void deleteByName(String nombre) {
		delete("nombre", nombre);
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
	
	private String getEntityName(){
		return getEntity().getSimpleName();
	}

	abstract protected Class<T> getEntity();
	// abstract protected String getId(T elemento);

}
