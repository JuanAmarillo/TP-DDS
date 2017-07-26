package integrationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import archivos.LevantaArchivoEmpresa;
import domain.*;
import domain.repositorios.RepositorioEmpresas;

public class EmpresaTest {
	public Empresa empresaA;
	public Empresa empresaB;
	public Empresa empresaC;

	private void prepararEmpresaB() {
		empresaB = new Empresa("Mocka-Cola");
		Set<Cuenta> cuentas = new HashSet<>();
		Cuenta cuentita = new Cuenta("ZZZ", "periodo", 12345.6);
		Cuenta cuentitaBis = new Cuenta("XXX", "periodo", 1000.0);
		cuentas.add(cuentita);
		cuentas.add(cuentitaBis);
		empresaB.setCuentas(cuentas);
	}

	LevantaArchivoEmpresa loader = mock(LevantaArchivoEmpresa.class);

	private void cargarArchivo(String ruta) throws IOException {
		loader.setFilepath(ruta);
		loader.cargarArchivo();
	}

	public List<Empresa> getListaEmpresas() {
		return RepositorioEmpresas.instance().getEmpresasCargadas();
	}

	@Before
	public void init() throws IOException {
		prepararEmpresaB();
		when(loader.getElementosDelArchivo()).thenReturn(empresaB);
		empresaA = loader.getElementosDelArchivo();
		RepositorioEmpresas.instance().agregarEmpresa(empresaA);
	}

	@After
	public void finalize() {
		RepositorioEmpresas.resetSingleton();
	}

	@Test
	public void testSeCargoElMock() {
		assertEquals(1, getListaEmpresas().size());
	}

	@Test
	public void testSeCargaronCuentasDelMock() {
		assertEquals(2, empresaA.getCuentas().size());
	}

	@Test
	public void testSeCargoUnSoloPeriodoYDosCuentas() {
		assertEquals(1, empresaA.getPeriodos().size());
	}

	@Test
	public void testBuscaAUnaEmpresaCargada() {
		Empresa empresa = new Empresa("Mocka-Cola");
		assertTrue(getListaEmpresas().stream().anyMatch(emp -> emp.getNombre().equals(empresa.getNombre())));
	}

	@Test(expected = RuntimeException.class)
	public void testObtenerEmpresaNoCargadaFalla() {
		Empresa empresa = new Empresa("Pirulo");
		RepositorioEmpresas.instance().buscarEmpresa(empresa.getNombre()).get();
	}

	@Test
	public void testRecargaDeUnaMismaEmpresaNoAgregaCuentas() throws IOException {
		cargarArchivo("ruta-loca");
		assertEquals(2, empresaA.getCuentas().size());
	}

	@Test
	public void testNoCargaDosVecesLaMismaEmpresa() throws IOException {
		cargarArchivo("ruta-loca");
		assertEquals(1, getListaEmpresas().size());
	}
}
