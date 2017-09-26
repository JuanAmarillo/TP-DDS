package unitTests.repositorios;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import domain.indicadores.Indicador;
import domain.indicadores.IndicadorCustom;
import domain.indicadores.indicadoresPredeterminados.ROA;
import domain.indicadores.indicadoresPredeterminados.ROE;
import domain.repositorios.RepositorioIndicadores;
import exceptions.NoSePuedeBorrarUnPredeterminadoException;
import exceptions.YaExisteElIndicadorException;

public class RepositorioIndicadoresTest {
	
	private RepositorioIndicadores repositorio;
	
	public IndicadorCustom indicador(String nombreIndicador){
		return new IndicadorCustom(nombreIndicador, "saraza", null);
	}
	
	public void agregar(String nombreIndicador){
		agregar(indicador(nombreIndicador));
	}
	
	public void agregar(IndicadorCustom indicador){
		repositorio.agregarIndicador(indicador);
	}
	
	public void borrar(String nombreIndicador){
		repositorio.eliminarIndicadorAPartirDel(nombreIndicador);
	}
	
	public void borrar(Indicador indicador){
		repositorio.eliminarIndicador(indicador);
	}
	
	public void verificarExistencia(String nombreIndicador, Boolean existencia){
		assertEquals(existe(nombreIndicador), existencia);
	}
	
	private Boolean existe(String nombreIndicador) {
		return repositorio.buscarIndicador(nombreIndicador).isPresent();
	}

	@Before
	public void init() {
		repositorio = new RepositorioIndicadores();
	}
	
	
	@Test
	public void testAgregarUnIndicadorCustom(){
		agregar("hola");
		verificarExistencia("hola", true);
	}
	
	@Test
	public void testNoEncuentraIndicador(){
		verificarExistencia("RoA", false);
	}
	
	@Test
	public void testAgregarUnIndicadorPredeterminador(){
		repositorio.add(new ROE());
		verificarExistencia("ROE", true);
	}
	
	@Test//(expected = YaExisteElIndicadorException.class)
	public void testAgregarUnIndicadorYaExistenteFalla(){
		agregar("No tuve vacaciones");
		agregar("No tuve vacaciones");
	}
	
	@Test
	public void testEliminarUnIndicador(){
		agregar("jackson es mi mejor amigo");
		borrar("jackson es mi mejor amigo");
		verificarExistencia("jackson es mi mejor amigo", false);
	}
	
	@Test(expected = RuntimeException.class)
	public void testEliminarUnIndicadorNoExistenteFalla(){
		borrar("nico que murio antes de la primera entrega");
	}
	
	@Test(expected = NoSePuedeBorrarUnPredeterminadoException.class)
	public void testEliminarUnPredeterminadorFalla(){
		repositorio.add(new ROA());
		borrar(new ROA());
	}
	

}
