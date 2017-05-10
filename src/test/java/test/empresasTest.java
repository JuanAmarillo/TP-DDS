package test;

import domain.*;
import domain.repositorios.RepositorioEmpresas;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import externos.LevantaArchivo;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.List;

public class empresasTest {
	// Auxiliares

	public Empresa cocaCola;

	private void cargarArchivo(String nombreArchivo) {
		try {
			new LevantaArchivo().cargarArchivo("src/test/resources/" + nombreArchivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Empresa> getListaEmpresas() {
		return RepositorioEmpresas.instance().getEmpresasCargadas();
	}

	private Empresa obtenerEmpresa(String nombreEmpresa) {
		Empresa aDevolver = RepositorioEmpresas.instance().buscarEmpresa(nombreEmpresa);
		return aDevolver;
	}

	@Before
	public void init() {
		cargarArchivo("Coca-Cola.json");
		cocaCola = obtenerEmpresa("Coca-Cola");
	}

	@After
	public void finalize() {
		RepositorioEmpresas.resetSingleton();
	}

	@Test
	public void testSeCargoCocaCola() {
		assertEquals(1, getListaEmpresas().size());
		assertEquals(2, cocaCola.getCuentas().size());
	}

	@Test
	public void testBuscaAUnaEmpresaCargada() {
		Empresa empresa = new Empresa();
		empresa.setNombre("Coca-Cola");
		assertTrue(getListaEmpresas().stream().anyMatch(emp -> emp.getNombre().equals(empresa.getNombre())));
	}

	@Test
	public void testSeCargaCorrectamentePepsi() throws IOException {
		cargarArchivo("Pepsi-co.json");
		Empresa emp = new Empresa();
		emp.setNombre("Pepsi-co");
		Empresa pepsiCo = RepositorioEmpresas.instance().buscarEmpresa(emp.getNombre());
		assertEquals("Pepsi-co", pepsiCo.getNombre());
		assertEquals(2, pepsiCo.getCuentas().size());
	}

	@Test
	public void testCocaColaTieneDosCuentas() {
		assertEquals(2, cocaCola.getCuentas().size());
	}

	@Test
	public void testPepsiCoTieneSoloUnPeriodoPeroDosCuentasTest() {
		cargarArchivo("Pepsi-co.json");
		Empresa pepsi = obtenerEmpresa("Pepsi-co");
		assertEquals(2, pepsi.getCuentas().size());
		assertEquals(1, pepsi.getPeriodos().size());

	}

	@Test
	public void testObtenerEmpresaNoCargada() {
		Empresa empresa = new Empresa();
		empresa.setNombre("Pirulo");
		assertEquals(null, RepositorioEmpresas.instance().buscarEmpresa(empresa.getNombre()));
	}

	@Test
	public void testRecargaDeUnaMismaEmpresaNoAgregaCuentas() {
		cargarArchivo("Coca-Cola.json");
		assertEquals(2, cocaCola.getCuentas().size());
	}

	@Test
	public void testMismaEmpresaAgregaCuentasDistintas() {
		cargarArchivo("Coca-Cola 2.json");
		assertEquals(4, cocaCola.getCuentas().size());
	}

	@Test
	public void testNoCargaDosVecesLaMismaEmpresa() {
		cargarArchivo("Coca-Cola.json");
		assertEquals(1, getListaEmpresas().size());
	}

}