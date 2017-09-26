package db;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionCustom;
import domain.condiciones.OperadoresCondicion.Mayor;
import domain.indicadores.indicadoresPredeterminados.Antiguedad;

public class CondicionesTest {

	private CondicionComparativa condicionCustom;
	private EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	
	@Before
	public void init() {
		condicionCustom = new CondicionComparativa("nombre", new Antiguedad(), new Mayor());
	}
	
	@Test
	public void persisteCondicionCustom() {
		entityManager.getTransaction().begin();
		entityManager.persist(condicionCustom);
		entityManager.getTransaction().commit();
	}
}
