package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Cuenta;
import domain.Empresa;
import domain.Indicador;
import domain.repositorios.RepositorioIndicadores;

public class IndicadoresTest {
	
	Indicador indicadorBuscado;
	Empresa emp;

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
	public void testBorrarUnIndicadorNoExistenteFalla(){
		eliminarIndicador("No te escucho soy de palo tengo orejas de pescado = 2");
	}

	@Test(expected = RuntimeException.class)
	public void testIndicadorConElMismoNombreEnSuEcuacionFalla() {
		cargarIndicadorYBuscarlo("indicador = indicador  +3  ");
	}

	@Test(expected = RuntimeException.class)
	public void testIndicadorConOperacionInvalidaFalla() {
		cargarIndicador("Buenos = !malos");
	}
	
	@Test(expected = RuntimeException.class)
	public void testIndicadorConVariasAsignacionesFalla(){
		cargarIndicador("Indicador = soy el verdadero = yo soy el verdadero");
	}
	
	@Test(expected = RuntimeException.class)
	public void testIndicadorSinAsignacionFalla(){
		cargarIndicador("hola");
	}

	@Test(expected = RuntimeException.class)
	public void testAgregarUnIndicadorYaExistenteFalla() {
		cargarIndicador("Legislacion = basura");
		cargarIndicador("Legislacion = mas basura");
	}
	
	@Test(expected = RuntimeException.class)
	public void testNoAdmiteRepetidos() {
		cargarIndicador("a = 23");
		cargarIndicador("a = 24");
		assertEquals(1,RepositorioIndicadores.instance().getIndicadoresCargados().size());
	}
}
