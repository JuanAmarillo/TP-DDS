package unitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.Empresa;
import domain.repositorios.RepositorioEmpresas;

import static org.junit.Assert.assertEquals;

public class RepositorioEmpresaTest {

	private RepositorioEmpresas repositorio;
	
	public void agregarEmpresa(String nombreEmpresa){
		repositorio.agregarEmpresa(new Empresa(nombreEmpresa));
	}
	
	private void buscarEmpresa(String nombre) {
		assertEquals(repositorio.buscarEmpresa(nombre).get().getNombre(),nombre);
	}
	
	public void existe(String nombre, boolean resultado) {
		assertEquals(repositorio.existeLaEmpresa(new Empresa(nombre)), resultado);
	}
	
	@Before
	public void init(){
		repositorio = new RepositorioEmpresas();
	}
	
	@Test
	public void testAgregaYBuscaLaEmpresa(){
		agregarEmpresa("Manaos");
		buscarEmpresa("Manaos");
	}
	
	@Test(expected = RuntimeException.class)
	public void testBuscaUnaEmpresaQueNoExisteFalla(){
		buscarEmpresa("Figureti");
	}
	
	@Test
	public void testExisteLaEmpresaManaos(){
		agregarEmpresa("Manaos");
		existe("Manaos",true);
	}

	@Test
	public void testNoExisteLaEmpresaFigureti(){
		existe("Figureti",false);
	}

	
	
}
