package db;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import domain.condiciones.Condicion;
import domain.condiciones.CondicionComparativa;
import domain.condiciones.OperadoresCondicion.Mayor;
import domain.indicadores.indicadoresPredeterminados.Antiguedad;

public class CondicionesTest {

	//private CondicionComparativa condicionCustom;
	private EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	private CondicionComparativa condicionCustom;
	
	@Before
	public void init() {
		condicionCustom = new CondicionComparativa("nombre", new Antiguedad(), new Mayor());
	}
	
	@Test
	public void persisteCondicionCustom() {
		entityManager.getTransaction().begin();
		entityManager.persist(condicionCustom);
		entityManager.getTransaction().commit();
		List<Condicion> condicionesCargadas = entityManager
				.createQuery("SELECT i FROM Condicion i", Condicion.class).getResultList();
		assertEquals(condicionesCargadas.size(), 1);
	}
}
