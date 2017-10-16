package unitTests.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import domain.login.Usuario;
import domain.metodologias.Metodologia;
import domain.repositorios.RepositorioMetodologias;
import domain.repositorios.RepositorioUsuarios;

public class RepositorioMetodologiasTest extends AbstractPersistenceTest{
	Usuario gabriel = new Usuario("Gabriel", "ElMasCapito", "soyyo");
	
	private RepositorioMetodologias repositorio;
	
	public void agregarMetodologia(String nombre){
		gabriel.agregarMetodologia(new Metodologia(nombre, null));
	}
	
	public void buscarMetodologia(String nombre){
		Usuario usuario = obtenerUsuario();
		assertTrue(usuario.getMetodologias().stream().anyMatch(met -> met.getNombre().equals(nombre)));
	}

	private Usuario obtenerUsuario() {
		return RepositorioUsuarios.instance().find("nombreCuenta", "ElMasCapito").get();
	}
	
	@Before
	public void init(){
		repositorio = new RepositorioMetodologias();
		RepositorioUsuarios.instance().agregar(gabriel); // Lo guardo ya que Metodologia contiene una fk
	}
	
	@Test
	public void testAgregarUnaMetodologiaSinVerificarQueExista(){
		agregarMetodologia("donde esta mi hamburguesa!");
		buscarMetodologia("donde esta mi hamburguesa!");
	}
	
	@Test
	public void testBuscaMetodologiaPepa(){
		agregarMetodologia("pepa");
		buscarMetodologia("pepa");
	}
	
	@Test(expected = AssertionError.class)
	public void testBuscarUnaMetodologiaNoExistenteFalla(){
		buscarMetodologia("no existos");
	}
	
	@Override
	public EntityManager entityManager() {
		return RepositorioMetodologias.instance().getEntityManager();
	}
	
	
	
}
