package unitTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import domain.condiciones.CondicionComparativa;
import domain.repositorios.RepositorioCondiciones;
import exceptions.NoSePuedeBorrarUnPredeterminadoException;

public class RepositorioCondicionesTest {
	//Originalmente el repo tiene 4

	@Test(expected = NoSePuedeBorrarUnPredeterminadoException.class)
	public void testNoSePuedeBorrarCondicionPredeterminada() {
		RepositorioCondiciones.instance().eliminarCondicion("Empresa de mas de 10 años");
	}
	
	@Test
	public void testSePuedeEliminarUnaCondicionCustom() {
		CondicionComparativa condicion = new CondicionComparativa("pepito",null,null);
		RepositorioCondiciones.instance().agregarCondicion(condicion);
		assertEquals(5,RepositorioCondiciones.instance().cantidadDeCondiciones());
		RepositorioCondiciones.instance().eliminarCondicion("pepito");
		assertEquals(4,RepositorioCondiciones.instance().cantidadDeCondiciones());
	}
	/*
	 * Depende de cuándo se ejecute falla, tema de hilos?
	@Test
	public void testSePuedeAgregarCondicionTaxativa(){
		CondicionTaxativa cond = new CondicionTaxativa("legis2",null,null,null);
		RepositorioCondiciones.agregarCondicion(cond);
		assertEquals(5,RepositorioCondiciones.instance().cantidadDeCondiciones());
	}
	*/
	@Test
	public void testSePuedeAgregarUnaCondicionComparativa(){
		CondicionComparativa condicion = new CondicionComparativa("legis",null,null);
		RepositorioCondiciones.instance().agregarCondicion(condicion);
		assertEquals(5, RepositorioCondiciones.instance().cantidadDeCondiciones());
	}
	
	@Test
	public void testSePuedeFiltrarPorTipoDeCondicion(){
		assertEquals(1, RepositorioCondiciones.instance().getCondicionesTaxativas().size());
		assertEquals(3, RepositorioCondiciones.instance().getCondicionesComparativas().size());
	}
	
	
	@Test
	public void testSeCarganTodasLasCondicionesPredeterminadas(){
		assertEquals(4, RepositorioCondiciones.instance().getCondicionesCargadas().size());
	}
	
}
