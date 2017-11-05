package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.HibernateException;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import exceptions.NoSePudoCargarAlRepositorioException;

public class Transaction {
	
	private static Transaction instance = null;
	protected EntityManager entityManager = PerThreadEntityManagers.getEntityManager();

	public static Transaction instance() {
		if (noHayInstanciaCargada())
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		instance = new Transaction();
	}

	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}

	public static void resetSingleton() {
		instance = null;
	}	

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void crear() {
		EntityTransaction tx = entityManager.getTransaction();
		if (!tx.isActive())
			tx.begin();
	}

	public void cerrar() {
		EntityTransaction tx = entityManager.getTransaction();
		terminarTransaccion(tx);
		resetEntityManager(tx);
	}

	private void terminarTransaccion(EntityTransaction tx) {
		try {
			commit(tx);
		} catch (HibernateException e) {
			rollback(tx);
		}
	}

	private void rollback(EntityTransaction tx) {
		tx.rollback();
		throw new NoSePudoCargarAlRepositorioException();
	}

	private void commit(EntityTransaction tx) {
		if (tx.isActive())
			tx.commit();
	}

	public void resetEntityManager(EntityTransaction tx) {
		if (tx.isActive())
		entityManager.flush();
	}
}
