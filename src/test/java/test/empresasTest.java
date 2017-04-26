package test;
import domain.*;
import domain.repositorios.RepositorioEmpresas;
import junit.framework.Assert;
import util.LevantaArchivo;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class empresasTest{
		
	private void cargarArchivo(String nombreArchivo) {
		try{
		RepositorioEmpresas.getInstance().agregarEmpresa(LevantaArchivo.cargarArchivo("src/test/resources/" + nombreArchivo));
		}
		catch(IOException e){e.printStackTrace();}
	}
	
	Empresa cocaCola;
	
	@Before
	public void init(){
		cargarArchivo("Empresa1.json");
		cocaCola= RepositorioEmpresas.getInstance().getEmpresasCargadas().get(0);
	}
	
	@After
	public void finalize(){
		RepositorioEmpresas.resetSingleton();
	}
	
	@Test
	public void seCargoCocaColaTest(){
		assertEquals(1, RepositorioEmpresas.getInstance().getEmpresasCargadas().size());
	}
	
	@Test
	public void cocaColaTieneDosCuentasTest(){
		assertEquals(2, cocaCola.getCuentas().size());
	}
	
	@Test
	public void cocaColaTieneSoloUnPeriodoPeroDosCuentasTest(){
		// Las cuentas cargadas en el json tienen el mismo periodo
		assertEquals(1, cocaCola.getPeriodos().size());
		assertEquals(2, cocaCola.getCuentas().size());
	}
	
	@Test
	public void cargarPepsiCoTest(){
		cargarArchivo("Empresa2.json");
		List<Empresa> lista = RepositorioEmpresas.getInstance().getEmpresasCargadas();
		assertEquals(2, lista.size());
	}
	
}