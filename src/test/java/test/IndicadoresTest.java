package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Indicador;
import domain.repositorios.RepositorioIndicadores;
import ui.vm.CargarIndicadorVM;

public class IndicadoresTest {
	CargarIndicadorVM indicadorVM;
	Indicador indicadorBuscado;
	
	@Before
	public void init(){
		this.indicadorVM = new CargarIndicadorVM();

	}
	@After
	public void finalize(){
		RepositorioIndicadores.resetSingleton();
	}
	
	public Indicador cargarIndicadorYBuscarlo(String indicador){
		indicadorVM.setIndicador(indicador);
		indicadorVM.cargarIndicador();
		String[] partesDelIndicador = indicador.split("=");
		return RepositorioIndicadores.instance().buscarIndicador(partesDelIndicador[0].trim());
	}
	
	@Test
	public void testSeCargaUnIndicador(){
		indicadorBuscado = cargarIndicadorYBuscarlo("Juanito y los Clonosaurios = dinousarios + clonacion");
		assertEquals(indicadorBuscado.nombreIndicador, "Juanito y los Clonosaurios");
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
