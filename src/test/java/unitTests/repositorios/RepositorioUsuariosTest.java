package unitTests.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import domain.login.Usuario;
import domain.repositorios.RepositorioMetodologias;
import domain.repositorios.RepositorioUsuarios;
import junit.framework.Assert;

public class RepositorioUsuariosTest extends AbstractPersistenceTest{
	
	private Usuario usuario;
	
	@Before
	public void init() {
		usuario = new Usuario("Gabriel", "gabrielElCapo", "jajajaja");
		RepositorioUsuarios.instance().agregar(usuario);
	}
	
	@Test
	public void seAgregaAlRepositorio() {
		assertEquals(1l, RepositorioUsuarios.instance().cantidadElementosCargados(), 0l);
	}
	
	@Test
	public void login() {
		RepositorioUsuarios.instance().login("gabrielElCapo", "jajajaja");
	}
	
	@Test(expected = RuntimeException.class)
	public void nombreIncorrecto() {
		RepositorioUsuarios.instance().login("NoSoyGabriel", "jajajaja");
	}
	
	@Test(expected = RuntimeException.class)
	public void contraseñaIncorrecta() {
		RepositorioUsuarios.instance().login("gabrielElCapo", "MalaPassword");
	}
	
	@Override
	public EntityManager entityManager() {
		return RepositorioMetodologias.instance().getEntityManager();
	}

}
