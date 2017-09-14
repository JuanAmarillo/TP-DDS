package db;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import domain.condiciones.Condicion;
import domain.condiciones.condicionesPredeterminadas.CEmpresaMayorAntiguedad;
import domain.condiciones.condicionesPredeterminadas.CEndeudamiento;
import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;
import domain.metodologias.Metodologia;

public class MetodologiasDBTest {

	Condicion condicionTAntiguedad = new TEmpresaMas10Años();
	Condicion condicionCEndeudamiento = new CEndeudamiento().setPeso(5.0);
	Condicion condicionCAntiguedad = new CEmpresaMayorAntiguedad().setPeso(10.0);

	List<Condicion> condicionesMixtas = Arrays.asList(condicionTAntiguedad, condicionCEndeudamiento);
	List<Condicion> condicionesTaxativas = Arrays.asList(condicionTAntiguedad);
	List<Condicion> condicionesComparativas = Arrays.asList(condicionCAntiguedad, condicionCEndeudamiento);

	Metodologia metodologiaMixta = new Metodologia("PepitaMixta", condicionesMixtas);
	Metodologia metodologiaTaxativa = new Metodologia("PepitaT", condicionesTaxativas);
	Metodologia metodologiaComparativa = new Metodologia("PepitaC", condicionesComparativas);

	private EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	
	
	@Test
	public void retornaMetodologia(){
		entityManager.getTransaction().begin();
		entityManager.persist(metodologiaMixta);
		entityManager.getTransaction().commit();
		List<Metodologia> metodologiasCargadas = entityManager.createQuery("SELECT i FROM Metodologia i", Metodologia.class).getResultList();
		assertEquals(metodologiasCargadas.size(),1);
	}

	
}
