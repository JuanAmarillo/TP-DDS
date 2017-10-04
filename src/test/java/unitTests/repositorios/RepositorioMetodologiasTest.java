package unitTests.repositorios;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import domain.metodologias.Metodologia;
import domain.repositorios.RepositorioMetodologias;

public class RepositorioMetodologiasTest extends AbstractPersistenceTest{
	
	private RepositorioMetodologias repositorio;
	
	public void agregarMetodologia(String nombre){
		repositorio.agregar(new Metodologia(nombre, null));
	}
	
	public void agregarMetodologiaSinVerificar(String nombre){
		repositorio.agregar(new Metodologia(nombre, null));
	}
	
	public void comprobarLaPrimeraMetodologia(String nombre){
		assertEquals(repositorio.getElementos().get(0).getNombre(),nombre);
	}
	
	public void buscarMetodologia(String nombre){
		assertEquals(repositorio.findByName(nombre).get().getNombre(),nombre);
	}
	
	@Before
	public void init(){
		repositorio = new RepositorioMetodologias();
	}
	
	@Test
	public void testAgregarUnaMetodologiaSinVerificarQueExista(){
		agregarMetodologiaSinVerificar("donde esta mi hamburguesa!");
		comprobarLaPrimeraMetodologia("donde esta mi hamburguesa!");
	}
	
	@Test
	public void testBuscaMetodologiaPepa(){
		agregarMetodologiaSinVerificar("pepa");
		buscarMetodologia("pepa");
	}
	
	@Test(expected = RuntimeException.class)
	public void testBuscarUnaMetodologiaNoExistenteFalla(){
		buscarMetodologia("no existos");
	}
	
	@Test
	public void testAgregaMetodologiaVerificandoExistencia(){
		agregarMetodologia("odiosos, babosos, ya no queremos osos");
		comprobarLaPrimeraMetodologia("odiosos, babosos, ya no queremos osos");
	}
	
	@Test(expected = EntityExistsException.class)
	public void testIntentarAgregarDosVecesLaMismaMetodologiaFalla(){
		agregarMetodologia("el autobus que tenia que ir rapido");
		agregarMetodologia("el autobus que tenia que ir rapido");
	}

	@Override
	public EntityManager entityManager() {
		return RepositorioMetodologias.instance().getEntityManager();
	}
	
	
	
}
