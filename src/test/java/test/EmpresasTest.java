package test;

import domain.*;
import domain.repositorios.RepositorioEmpresas;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import externos.LevantaArchivoEmpresa;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.List;

public class EmpresasTest {
	// Auxiliares

	public Empresa cocaCola;

	private void cargarArchivo(String nombreArchivo) {
		try {
			new LevantaArchivoEmpresa().cargarArchivo("src/test/resources/" + nombreArchivo);
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
	public void testMismaEmpresaAgregaCuentasDistintas() {
		cargarArchivo("Coca-Cola 2.json");
		assertEquals(4, cocaCola.getCuentas().size());
	}


}