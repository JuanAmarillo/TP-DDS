package test;
import domain.*;
import util.LevantaArchivo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.repositorios.RepositorioEmpresas;
import exceptions.AlreadyUploadedException;
import exceptions.NoExisteLaEmpresaException;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class empresasTest{
	// Auxiliares	
	
	public Empresa cocaCola;
	
	private void cargarArchivo(String nombreArchivo) {
		try{
			(new LevantaArchivo()).cargarArchivo("src/test/resources/" + nombreArchivo);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(AlreadyUploadedException e) {
			e.printStackTrace();
		}
	}
	
	public List<Empresa> getListaEmpresas() {
		return RepositorioEmpresas.getInstance().getEmpresasCargadas();
	}

	@Before
	public void init() {
		cargarArchivo("Coca-Cola.json");
		cocaCola = getListaEmpresas().get(0);
	}

	@After
	public void finalize() {
		RepositorioEmpresas.resetSingleton();
	}

	@Test
	public void seCargoCocaColaTest() {
		assertEquals(1, getListaEmpresas().size());
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
		Empresa pepsiCo = RepositorioEmpresas.getInstance().getEmpresaCargada(emp);
		assertEquals("Pepsi-co",pepsiCo.getNombre());
		assertEquals(2,pepsiCo.getCuentas().size());
	}
	
	@Test
	public void cocaColaTieneDosCuentasTest() {
		assertEquals(2, cocaCola.getCuentas().size());
	}
	
	@Test
	public void cocaColaTieneSoloUnPeriodoPeroDosCuentasTest() {
		// Las cuentas cargadas en el json tienen el mismo periodo PREGUNTAR
		assertEquals(2, cocaCola.getPeriodos().size());
		assertEquals(2, cocaCola.getCuentas().size());
	}
	
	@Test(expected=NoExisteLaEmpresaException.class)
	public void testObtenerEmpresaNoCargada(){
		Empresa empresa = new Empresa();
		empresa.setNombre("Pirulo");
		RepositorioEmpresas.getInstance().getEmpresaCargada(empresa);
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
	
}