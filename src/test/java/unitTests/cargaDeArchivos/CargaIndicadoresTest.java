package unitTests.cargaDeArchivos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import archivos.LevantaArchivoIndicadores;
import auxiliaresDeArchivo.DatosIndicadores;
import domain.repositorios.RepositorioIndicadores;

public class CargaIndicadoresTest {
	
	@Before
	public void init() {
		RepositorioIndicadores.resetSingleton();
	}
	
	public void asertarCantidad(int esperado, int cantidad) {
		assertEquals(esperado, cantidad);
	}
	
	public int cantidadIndicadores() {
		return RepositorioIndicadores.instance().getIndicadoresCargados().size();
	}
	
	public void cargarArchivo() throws IOException{
		new LevantaArchivoIndicadores("src/test/resources/Indicadores.json").cargarArchivo();
	}
	
	@Test
	public void testObtieneElementosDelArchivo() throws IOException {
		DatosIndicadores di = new LevantaArchivoIndicadores("src/test/resources/Indicadores.json").getElementosDelArchivo();
		asertarCantidad(di.getIndicadores().size(), 1);
	}
	
	@Test
	public void testCargaDesdeArchivo() throws IOException{
		cargarArchivo();
		asertarCantidad(cantidadIndicadores(), 8);
	}
	
	@Test(expected = IOException.class)
	public void testFormatoInvalido() throws IOException {
		new LevantaArchivoIndicadores("src/test/resources/Coca-Cola.json").cargarArchivo();
	}
	
	@Test
	public void testSePuedeCalcular() throws IOException {
		cargarArchivo();
		assertTrue(RepositorioIndicadores.instance().getIndicadoresCargados().get(7).calcular(null, null).getValorExito().get().equals("27.0"));
	}
	
}
