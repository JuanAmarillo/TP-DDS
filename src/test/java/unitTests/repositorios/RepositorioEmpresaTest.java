package unitTests.repositorios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Empresa;
import domain.repositorios.RepositorioEmpresas;
import junit.framework.Assert;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;

public class RepositorioEmpresaTest {

	private RepositorioEmpresas repositorio;

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
		assertEquals(resultado,repositorio.existeLaEmpresa(crearEmpresa(nombre)));
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
	
	private void imprimirEmpresas(String test) {
		List<Empresa> empresas = repositorio.getElementos();
		System.out.println(empresas.size());
		empresas.forEach(empresa -> System.out.println(test + "   AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + empresa.getNombre()));
	}

	public void agregarEmpresaLuegoDeArchivo(String nombre) {
		repositorio.agregarDesdeArchivo(crearEmpresa(nombre));
	}
	
	@Before
	public void init() {
		repositorio = new RepositorioEmpresas();
		agregarEmpresa("Steam");
	}
	
	@After
	public void finalize() {
		borrarEmpresa("Steam");
	}
	
	@Test
	public void tieneEmpresasCargadas(){
		laCantidadDeEmpresasCargadasEs(1l);
	}

	@Test
	public void testAgregaTresEmpresasDistintasLuegoDeArchivo() {
		agregarEmpresaLuegoDeArchivo("Universidad");
		agregarEmpresaLuegoDeArchivo("Tecnologica");
		agregarEmpresaLuegoDeArchivo("Nacional");
		laCantidadDeEmpresasCargadasEs(4l); // Universidad, Tecnologica, Nacional y Steam
		borrarEmpresa("Universidad");
		borrarEmpresa("Tecnologica");
		borrarEmpresa("Nacional");
	}

	

	@Test
	public void testAgregaDosVecesLaMismaEmpresaLuegoDeArchivoSoloDejaUna() {
		agregarEmpresaLuegoDeArchivo("Jackson");
		agregarEmpresaLuegoDeArchivo("Jackson");
		imprimirEmpresas("Jackson");
		laCantidadDeEmpresasCargadasEs(2l); //Jackson y Steam
		borrarEmpresa("Jackson");
	}

	@Test
	public void testAgregaYBuscaLaEmpresa() {
		agregarEmpresa("Manaos");
		buscarEmpresa("Manaos");
		borrarEmpresa("Manaos");
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
