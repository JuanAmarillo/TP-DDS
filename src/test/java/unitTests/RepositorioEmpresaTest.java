package unitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.Empresa;
import domain.repositorios.RepositorioEmpresas;

import static org.junit.Assert.assertEquals;

public class RepositorioEmpresaTest {

	private RepositorioEmpresas repositorio;

	public void agregarEmpresa(String nombreEmpresa) {
		repositorio.agregarEmpresa(crearEmpresa(nombreEmpresa));
	}

	public Empresa crearEmpresa(String nombreEmpresa) {
		return new Empresa(nombreEmpresa);
	}

	private void buscarEmpresa(String nombre) {
		assertEquals(repositorio.buscarEmpresa(nombre).get().getNombre(), nombre);
	}

	public void verificarExistencia(String nombre, boolean resultado) {
		assertEquals(repositorio.existeLaEmpresa(crearEmpresa(nombre)), resultado);
	}

	public void comprobarLaPrimeraEmpresa(String nombre) {
		assertEquals(repositorio.getEmpresasCargadas().get(0).getNombre(), nombre);
	}

	public void laCantidadDeEmpresasCargadasEs(Integer cantidad) {
		assertEquals(repositorio.cantidadDeEmpresasCargadas(), cantidad);
	}

	public void agregarEmpresaLuegoDeArchivo(String nombre) {
		repositorio.agregarDesdeArchivo(crearEmpresa(nombre));
	}

	@Before
	public void init() {
		repositorio = new RepositorioEmpresas();
	}

	@Test
	public void testAgregaLaEmpresaSteamConExito() {
		agregarEmpresa("Steam");
		comprobarLaPrimeraEmpresa("Steam");
	}

	@Test
	public void testAgregaTresEmpresasDistintasLuegoDeArchivo() {
		agregarEmpresaLuegoDeArchivo("Universidad");
		agregarEmpresaLuegoDeArchivo("Tecnologica");
		agregarEmpresaLuegoDeArchivo("Nacional");
		laCantidadDeEmpresasCargadasEs(3);
	}

	@Test
	public void testAgregaDosVecesLaMismaEmpresaLuegoDeArchivoSoloDejaUna() {
		agregarEmpresaLuegoDeArchivo("Jackson");
		agregarEmpresaLuegoDeArchivo("Jackson");
		laCantidadDeEmpresasCargadasEs(1);
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
	}

	@Test
	public void testNoExisteLaEmpresaFigureti() {
		verificarExistencia("Figureti", false);
	}

}
