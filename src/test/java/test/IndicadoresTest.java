package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Indicador;
import domain.repositorios.RepositorioIndicadores;

public class IndicadoresTest {
	Indicador indicadorBuscado;

	public void cargarIndicador(String indicador) {
		RepositorioIndicadores.instance().agregarIndicadorAPartirDe(indicador);
	}

	public void eliminarIndicador(String nombre) {
		RepositorioIndicadores.instance().eliminarIndicadorAPartirDe(nombre);
	}

	public Indicador buscarIndicador(String indicador) {
		String nombre = Indicador.getNombre(indicador);
		return RepositorioIndicadores.instance().buscarIndicador(nombre);
	}

	public Indicador cargarIndicadorYBuscarlo(String indicador) {
		cargarIndicador(indicador);
		return buscarIndicador(indicador);
	}

	@Before
	public void init() {

	}

	@After
	public void finalize() {
		RepositorioIndicadores.resetSingleton();
	}

	@Test
	public void testSeCargaUnIndicador() {
		indicadorBuscado = cargarIndicadorYBuscarlo("Juanito y los Clonosaurios = dinousarios + clonacion");
		assertEquals(indicadorBuscado.nombre, "Juanito y los Clonosaurios");
		assertEquals(indicadorBuscado.ecuacion, " dinousarios + clonacion");
		assertEquals(1, RepositorioIndicadores.instance().getIndicadoresCargados().size());
	}

	@Test
	public void testSeBorraUnIndicador() {
		cargarIndicador("Juanito y los Clonosaurios = dinousarios + clonacion");
		eliminarIndicador("Juanito y los Clonosaurios");
		assertEquals(0,RepositorioIndicadores.instance().getIndicadoresCargados().size());

	}

	@Test(expected = RuntimeException.class)
	public void testIndicadorConElMismoNombreEnSuEcuacionFalla() {
		cargarIndicadorYBuscarlo("indicador = indicador    ");
	}

	@Test(expected = RuntimeException.class)
	public void testIndicadorConOperacionInvalidaFalla() {
		cargarIndicadorYBuscarlo("Buenos = !malos");
	}

	@Test(expected = RuntimeException.class)
	public void testAgregarUnIndicadorYaExistenteFalla() {
		cargarIndicadorYBuscarlo("Legislacion = basura");
		cargarIndicadorYBuscarlo("Legislacion = mas basura");
	}

}
