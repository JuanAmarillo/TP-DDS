package integrationTest;

import domain.*;
import domain.repositorios.RepositorioEmpresas;
import domain.repositorios.RepositorioIndicadores;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import archivos.LevantaArchivoEmpresa;
import archivos.LevantaArchivoIndicadores;

import static org.junit.Assert.*;
import java.io.IOException;

public class CargaEmpresaTest {

	public Empresa cocaCola;

	private void cargarArchivos(String nombreArchivo) {
		try {
			new LevantaArchivoEmpresa("src/test/resources/" + nombreArchivo).cargarArchivo();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Before
	public void init() {
		cargarArchivos("Coca-Cola.json");
	}

	@After
	public void finalize() {
		RepositorioEmpresas.resetSingleton();
	}
	
	@Test
	public void testMismaEmpresaAgregaCuentasDistintas() {	
		cargarArchivos("Coca-Cola 2.json");
		assertEquals(12, RepositorioEmpresas.instance().buscarEmpresa("Coca-Cola").get().getCuentas().size());
	}

}