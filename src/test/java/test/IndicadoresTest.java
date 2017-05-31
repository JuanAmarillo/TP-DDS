package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Indicador;
import domain.repositorios.RepositorioIndicadores;
import ui.vm.CargarIndicadorVM;

public class IndicadoresTest {
	Indicador indicadorBuscado;
	
	@Before
	public void init(){
		

	}
	@After
	public void finalize(){
		RepositorioIndicadores.resetSingleton();
	}
	
	public void cargarIndicador(String indicador){
		RepositorioIndicadores.instance().agregarIndicadorAPartirDe(indicador);
	}
	
	public Indicador buscarIndicador(String indicador){
		String nombre = Indicador.getNombre(indicador);
		return RepositorioIndicadores.instance().buscarIndicador(nombre);
	}
	
	public Indicador cargarIndicadorYBuscarlo(String indicador){
		cargarIndicador(indicador);
		return buscarIndicador(indicador);
	}
	
	@Test
	public void testSeCargaUnIndicador(){
		indicadorBuscado = cargarIndicadorYBuscarlo("Juanito y los Clonosaurios = dinousarios + clonacion");
		assertEquals(indicadorBuscado.nombre, "Juanito y los Clonosaurios");
		assertEquals(indicadorBuscado.ecuacion, " dinousarios + clonacion");
	}
	
	@Test(expected = RuntimeException.class)
	public void testIndicadorConElMismoNombreEnSuEcuacionFalla(){
		cargarIndicadorYBuscarlo("indicador = indicador    ");
	}
	
	@Test(expected = RuntimeException.class)
	public void testIndicadorConOperacionInvalidaFalla(){
		cargarIndicadorYBuscarlo("Buenos = !malos");
	}
	
	@Test(expected = RuntimeException.class)
	public void testAgregarUnIndicadorYaExistenteFalla(){
		cargarIndicadorYBuscarlo("Legislacion = basura");
		cargarIndicadorYBuscarlo("Legislacion = mas basura");
	}
}
