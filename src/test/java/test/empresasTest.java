package test;
import domain.*;
import domain.repositorios.RepositorioEmpresas;
import util.AlreadyUploadedException;
import util.LevantaArchivo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;


public class empresasTest{
		
	private void cargarArchivo(String nombreArchivo) {
		try{
			RepositorioEmpresas.getInstance().agregarEmpresa((new LevantaArchivo()).cargarArchivo("src/test/resources/" + nombreArchivo));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(AlreadyUploadedException e) {
			e.printStackTrace();
		}
	}
	
	Empresa cocaCola;
	
	public List<Empresa> getListaEmpresas() {
		return RepositorioEmpresas.getInstance().getEmpresasCargadas();
	}
	
	@Before
	public void init() {
		cargarArchivo("Empresa1.json");
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
	public void testSeCargaCorrectamentePepsi() throws IOException {
		Empresa pepsiCo = ((new LevantaArchivo()).getEmpresaDelArchivo("Empresa2.json"));
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
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testObtenerEmpresaNoCargada(){
		Empresa empresa = new Empresa();
		empresa.setNombre("Pirulo");
		RepositorioEmpresas.getInstance().obtenerEmpresaYaCargada(empresa);
	}
	
	@Test
	public void testRecargaDeUnaMismaEmpresaNoAgregaCuentas() {
		cargarArchivo("Empresa1.json");
		assertEquals(2, cocaCola.getCuentas().size());
	}
	
	@Test
	public void testMismaEmpresaAgregaCuentasDistintas() {
		cargarArchivo("Empresa3.json");
		assertEquals(4, cocaCola.getCuentas().size());
	}
	
}