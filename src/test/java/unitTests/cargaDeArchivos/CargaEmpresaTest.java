package unitTests.cargaDeArchivos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import domain.repositorios.RepositorioEmpresas;
import persistencia.LevantaArchivoEmpresa;

public class CargaEmpresaTest extends AbstractPersistenceTest {

	public RepositorioEmpresas repositorio = new RepositorioEmpresas();

	private void cargarArchivo(String nombreArchivo) {
		try {
			new LevantaArchivoEmpresa("src/test/resources/" + nombreArchivo).cargarArchivo();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public EntityManager entityManager() {
		return repositorio.getEntityManager();
	}
	
	@Test
	public void testCargaUnaEmpresa(){
		cargarArchivo("Coca-Cola.json");
		assertTrue(repositorio.findByName("Coca-Cola").isPresent());
	}
	
	@Test
	public void testMismaEmpresaAgregaCuentasDistintas() {	
		cargarArchivo("Coca-Cola 2.json");
		cargarArchivo("Coca-Cola.json");
		assertEquals(12, repositorio.findByName("Coca-Cola").get().getCuentas().size());
	}


	
}