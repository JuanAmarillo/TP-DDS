package domain.repositorios;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import domain.Empresa;

public abstract class Repositorio<T> {

	protected EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	
	@SuppressWarnings("unchecked")
	public List<T> obtenerLista(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	public List<T> getElementos() {
		return obtenerLista("from " + this.getEntityName());
	}

	public void agregar(T elemento) {
		EntityTransaction tx = crearTransaccion();
		persistir(elemento);
		tx.commit();
	}

	protected EntityTransaction crearTransaccion() {
		EntityTransaction tx = entityManager.getTransaction();
		if (!tx.isActive())
			tx.begin();
		return tx;
	}

	private void persistir(T elemento) {
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
		return find("id",id.toString());
	}

	protected Optional<T> find(String donde,String elemento) {
		return obtenerLista( from() + where(donde, elemento) ).stream().findFirst();
	}
	
	private String from(){
		return "from "+ getEntityName() + " " ;
	}
	
	private String where(String donde, String elemento){
		return "where " + donde + "= '" + elemento + "'";
	}

	public void deleteById(Integer id) {
		delete("id", id.toString());
	}

	protected void delete(String donde, String elemento) {
		EntityTransaction tx = crearTransaccion();
		entityManager.createQuery("delete from Empresa " + where(donde, elemento)).executeUpdate();
		tx.commit();
	}

	abstract protected String getEntityName();
	// abstract protected String getId(T elemento);

}
