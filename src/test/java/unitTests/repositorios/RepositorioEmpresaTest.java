package unitTests.repositorios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import domain.Empresa;
import domain.repositorios.RepositorioEmpresas;
import junit.framework.Assert;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;

public class RepositorioEmpresaTest extends AbstractPersistenceTest {

	private RepositorioEmpresas repositorio = new RepositorioEmpresas();

	public void agregarEmpresa(String nombreEmpresa) {
		repositorio.agregar(crearEmpresa(nombreEmpresa));
	}

	public Empresa crearEmpresa(String nombreEmpresa) {
		return new Empresa(nombreEmpresa);
	}

	private void buscarEmpresa(String nombre) {
		assertEquals(repositorio.findByName(nombre).get().getNombre(), nombre);
	}

	public void verificarExistencia(String nombre, boolean resultado) {
		assertEquals(resultado,repositorio.verificarExistencia(crearEmpresa(nombre)));
	}

	public void comprobarLaPrimeraEmpresa(String nombre) {
		assertEquals(repositorio.getElementos().get(0).getNombre(), nombre);
	}

	public void laCantidadDeEmpresasCargadasEs(Long cantidad) {
		assertEquals(cantidad, repositorio.cantidadElementosCargados());
	}
	
	private void borrarEmpresa(String empresa) {
		repositorio.deleteByName(empresa);
	}

	public void agregarEmpresaLuegoDeArchivo(String nombre) {
		repositorio.agregar(crearEmpresa(nombre));
	}
	
	@Override
	public EntityManager entityManager() {
		return repositorio.getEntityManager();
	}
	
	@Test
	public void tieneEmpresasCargadas(){
		laCantidadDeEmpresasCargadasEs(0l);
	}

	@Test
	public void testAgregaTresEmpresasDistintasLuegoDeArchivo() {
		agregarEmpresaLuegoDeArchivo("no metas al Sr Burns en esto");
		agregarEmpresaLuegoDeArchivo("ya comete la maldita naranja");
		agregarEmpresaLuegoDeArchivo("Nacional");
		laCantidadDeEmpresasCargadasEs(3l);
	}

	@Test
	public void testAgregaDosVecesLaMismaEmpresaLuegoDeArchivoSoloDejaUna() {
		agregarEmpresaLuegoDeArchivo("Jackson");
		agregarEmpresaLuegoDeArchivo("Jackson");
		laCantidadDeEmpresasCargadasEs(1l);
	}
	
	@Test
	public void testBorraUnaEmpresa(){
		agregarEmpresa("peron");
		borrarEmpresa("peron");
		laCantidadDeEmpresasCargadasEs(0l);
	}

	@Test
	public void testAgregaYBuscaLaEmpresa() {
		agregarEmpresa("Manaos");
		buscarEmpresa("Manaos");
	}

	@Test(expected = RuntimeException.class)
	public void testBuscaUnaEmpresaQueNoExisteFalla() {
		buscarEmpresa("Figureti");
	}

	@Test
	public void testExisteLaEmpresaManaos() {
		agregarEmpresa("Manaos");
		verificarExistencia("Manaos", true);
		borrarEmpresa("Manaos");
	}

	@Test
	public void testNoExisteLaEmpresaFigureti() {
		verificarExistencia("Figureti", false);
	}
	
}
