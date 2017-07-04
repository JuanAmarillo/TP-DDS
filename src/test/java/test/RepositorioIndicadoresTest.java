package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.indicadores.BuilderIndicadorCustom;
import domain.indicadores.Indicador;
import domain.indicadores.IndicadorCustom;
import domain.repositorios.RepositorioIndicadores;
import exceptions.ParsingException;

public class RepositorioIndicadoresTest {
	
	Indicador indicadorBuscado;

	public void cargarIndicador(String indicador) {
		RepositorioIndicadores.instance().agregarIndicadorAPartirDe(indicador);
	}

	public void eliminarIndicador(String nombre) {
		RepositorioIndicadores.instance().eliminarIndicadorAPartirDe(nombre);
	}

	public Indicador buscarIndicador(String indicador) {
		BuilderIndicadorCustom builderNombre = new BuilderIndicadorCustom(indicador);
		String nombre = builderNombre.analizar().generarIndicador().getNombre();
		return RepositorioIndicadores.instance().buscarIndicador(nombre).get();
	}

	public Indicador cargarIndicadorYBuscarlo(String indicador) {
		cargarIndicador(indicador);
		return buscarIndicador(indicador);
	}
	
	@Before
	public void init(){
		RepositorioIndicadores.resetSingleton();
	}
	

	@Test
	public void testSeCargaUnIndicador() {
		cargarIndicador("Juanito y los Clonosaurios = dinousarios + clonacion");
		indicadorBuscado = buscarIndicador("Juanito y los Clonosaurios = dinousarios + clonacion");
		assertEquals(indicadorBuscado.getNombre(), "Juanito y los Clonosaurios");
		assertEquals(indicadorBuscado.getEcuacion(), "dinousarios + clonacion");
		assertEquals(5, RepositorioIndicadores.instance().getIndicadoresCargados().size());
	}

	@Test
	public void testSeBorraUnIndicador() {
		cargarIndicador("Juanito y los Clonosaurios = dinousarios + clonacion");
		eliminarIndicador("Juanito y los Clonosaurios");
		assertEquals(4,RepositorioIndicadores.instance().getIndicadoresCargados().size());
	}
	
	@Test(expected = RuntimeException.class)
	public void testBorrarUnIndicadorNoExistenteFalla(){
		eliminarIndicador("No te escucho soy de palo tengo orejas de pescado = 2");
	}

	@Test(expected = ParsingException.class)
	public void testIndicadorConElMismoNombreEnSuEcuacionFalla() {
		cargarIndicadorYBuscarlo("indicador = indicador  +3  ");
	}

	@Test(expected = ParsingException.class)
	public void testIndicadorConOperacionInvalidaFalla() {
		cargarIndicador("Buenos = !malos");
	}
	
	@Test(expected = ParsingException.class)
	public void testIndicadorConVariasAsignacionesFalla(){
		cargarIndicador("Indicador = soy el verdadero = yo soy el verdadero");
	}
	
	@Test(expected = ParsingException.class)
	public void testIndicadorSinAsignacionFalla(){
		cargarIndicador("hola");
	}
	
	@Test(expected = ParsingException.class)
	public void testIndicadorConParentesisFaltanteFalla(){
		cargarIndicador("hola = ((2+5)*3");
	}

	@Test(expected = RuntimeException.class)
	public void testAgregarUnIndicadorYaExistenteFalla() {
		cargarIndicador("Legislacion = basura");
		cargarIndicador("Legislacion = mas basura");
	}
	

}
