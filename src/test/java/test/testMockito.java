package test;

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

import domain.*;
import domain.repositorios.RepositorioEmpresas;
import externos.LevantaArchivo;

public class testMockito {
	public Empresa empresaMockeadaA;
	public Empresa empresaMockeadaB;
	public Cuenta cuentita;
	public Set<Cuenta> cuentas = new HashSet<>();
	
	private void prepararEmpresa() {
		cuentita.setNombre("ZZZ");
		cuentita.setPeriodo("periodo");
		cuentita.setBalance(12345.6f);
		empresaMockeadaB.setNombre("Mocka-Cola");
		cuentas.add(cuentita);
		empresaMockeadaB.setCuentas(cuentas);
	}
	
	LevantaArchivo loader = mock(LevantaArchivo.class);
	when(loader.getEmpresaDelArchivo(anyString())).thenReturn(empresaMockeadaB);
	
	private void cargarArchivo(String ruta) {
		try {
			loader.cargarArchivo(ruta);
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
		cargarArchivo("ruta-loca");
		empresaMockeadaA = obtenerEmpresa("Mocka-Cola");
	}
	
	@After
	public void finalize() {
		RepositorioEmpresas.resetSingleton();
	}
/*	
	@Test
	public void testAAA() {
	LevantaArchivo loader = mock(LevantaArchivo.class);
	when(loader.getEmpresaDelArchivo(anyString())).thenReturn(empresaMockeadaB);
		
	}
*/
	@Test
	public void testSeCargoElMock() {
		assertEquals(1, getListaEmpresas().size());
		assertEquals(1, empresaMockeadaA.getCuentas().size());
	}

	@Test
	public void testBuscaAUnaEmpresaCargada() {
		Empresa empresa = new Empresa();
		empresa.setNombre("Mocka-Cola");
		assertTrue(getListaEmpresas().stream().anyMatch(emp -> emp.getNombre().equals(empresa.getNombre())));
	}

	@Test
	public void testMockaTieneUnaCuenta() {
		assertEquals(1, empresaMockeadaA.getCuentas().size());
	}

	@Test
	public void testObtenerEmpresaNoCargada() {
		Empresa empresa = new Empresa();
		empresa.setNombre("Pirulo");
		assertEquals(null, RepositorioEmpresas.instance().buscarEmpresa(empresa.getNombre()));
	}

	@Test
	public void testRecargaDeUnaMismaEmpresaNoAgregaCuentas() {
		cargarArchivo("ruta-loca");
		assertEquals(1, empresaMockeadaA.getCuentas().size());
	}

	@Test
	public void testNoCargaDosVecesLaMismaEmpresa() {
		cargarArchivo("ruta-loca");
		assertEquals(1, getListaEmpresas().size());
	}
}
