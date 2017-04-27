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
			RepositorioEmpresas.getInstance().agregarEmpresa(LevantaArchivo.cargarArchivo("src/test/resources/" + nombreArchivo));
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
		cocaCola= getListaEmpresas().get(0);
	}
	
	@After
	public void finalize() {
		RepositorioEmpresas.resetSingleton();
		LevantaArchivo.resetSingleton();
	}
	
	@Test
	public void seCargoCocaColaTest() {
		assertEquals(1, getListaEmpresas().size());
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
	
	@Test
	public void cargarPepsiCoTest() {
		cargarArchivo("Empresa2.json");;
		assertEquals(2, getListaEmpresas().size());
	}
	
}