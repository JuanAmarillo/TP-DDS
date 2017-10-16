package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.HibernateException;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import exceptions.NoSePudoCargarAlRepositorioException;

public class TransactionManager {
	
	private static TransactionManager instance = null;
	protected EntityManager entityManager = PerThreadEntityManagers.getEntityManager();

	public static TransactionManager instance() {
		if (noHayInstanciaCargada())
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		instance = new TransactionManager();
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

	public void crearTransaccion() {
		EntityTransaction tx = entityManager.getTransaction();
		if (!tx.isActive())
			tx.begin();
	}

	public void cerrarTransaccion() {
		EntityTransaction tx = entityManager.getTransaction();
		terminarTransaccion(tx);
		resetEntityManager();
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
		if (!tx.isActive())
			tx.commit();
	}

	public void resetEntityManager() {
		entityManager.flush();
	}
}
