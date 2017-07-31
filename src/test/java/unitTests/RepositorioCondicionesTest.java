package unitTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import domain.condiciones.CondicionComparativa;
import domain.repositorios.RepositorioCondiciones;
import exceptions.NoSePuedeBorrarUnPredeterminadoException;

public class RepositorioCondicionesTest {
	
	@Test(expected = NoSePuedeBorrarUnPredeterminadoException.class)
	public void testNoSePuedeBorrarCondicionPredeterminada() {
		RepositorioCondiciones.instance().eliminarCondicion("Taxativa - Empresa de mas de 10 años");
	}
	
	@Test
	public void testSePuedeEliminarUnaCondicion() {
		CondicionComparativa condicion = new CondicionComparativa("pepito",null,null);
		RepositorioCondiciones.instance().agregarCondicion(condicion);
		assertEquals(5,RepositorioCondiciones.instance().cantidadDeCondiciones());
		RepositorioCondiciones.instance().eliminarCondicion("Comparativa - pepito");
		assertEquals(4,RepositorioCondiciones.instance().cantidadDeCondiciones());
	}
}
