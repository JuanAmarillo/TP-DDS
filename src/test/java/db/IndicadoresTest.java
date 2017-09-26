package db;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import domain.indicadores.IndicadorCustom;

public class IndicadoresTest {
	private EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	private IndicadorCustom indicadorCustom;
	
	@Before
	public void init() {
		indicadorCustom = new IndicadorCustom();
		indicadorCustom.setNombre("MiNombreEsIndicador");
		indicadorCustom.setExpresion("MiNombreEsIndicador = 42");
	}
	
	@After
	public void finalize() {
		entityManager.clear();
	}
	
	@Test
	public void persisteIndicadorCustom() {
		entityManager.getTransaction().begin();
		entityManager.persist(indicadorCustom);
		entityManager.getTransaction().commit();
	}
	
}
