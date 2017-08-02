package unitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.metodologias.Metodologia;
import domain.repositorios.RepositorioMetodologias;
import exceptions.YaExisteLaMetodologiaException;

public class RepositorioMetodologiasTest {
	
	private RepositorioMetodologias repositorio;
	
	public void agregarMetodologia(String nombre){
		repositorio.agregarMetodologia(new Metodologia(nombre, null));
	}
	
	public void agregarMetodologiaSinVerificar(String nombre){
		repositorio.add(new Metodologia(nombre, null));
	}
	
	public void comprobarLaPrimeraMetodologia(String nombre){
		assertEquals(repositorio.getMetodologiasCargadas().get(0).getNombre(),nombre);
	}
	
	public void buscarMetodologia(String nombre){
		assertEquals(repositorio.buscarMetodologia(nombre).get().getNombre(),nombre);
	}
	
	@Before
	public void init(){
		repositorio = new RepositorioMetodologias();
	}
	
	@Test
	public void testAgregarUnaMetodologiaSinVerificarQueExista(){
		agregarMetodologiaSinVerificar("donde esta mi hamburguesa!");
		comprobarLaPrimeraMetodologia("donde esta mi hamburguesa!");
	}
	
	@Test
	public void testBuscaMetodologiaPepa(){
		agregarMetodologiaSinVerificar("pepa");
		buscarMetodologia("pepa");
	}
	
	@Test(expected = RuntimeException.class)
	public void testBuscarUnaMetodologiaNoExistenteFalla(){
		buscarMetodologia("no existos");
	}
	
	@Test
	public void testAgregaMetodologiaVerificandoExistencia(){
		agregarMetodologia("odiosos, babosos, ya no queremos osos");
		comprobarLaPrimeraMetodologia("odiosos, babosos, ya no queremos osos");
	}
	
	@Test(expected = YaExisteLaMetodologiaException.class)
	public void testIntentarAgregarDosVecesLaMismaMetodologiaFalla(){
		agregarMetodologia("el autobus que tenia que ir rapido");
		agregarMetodologia("el autobus que tenia que ir rapido");
	}
	
	
	
}
