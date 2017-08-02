package unitTests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import domain.condiciones.Condicion;
import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionTaxativa;
import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;
import domain.repositorios.RepositorioCondiciones;
import exceptions.NoSePuedeBorrarUnPredeterminadoException;

public class RepositorioCondicionesTest {
	
	private RepositorioCondiciones repositorio;
	
	public CondicionComparativa crearCondicionComparativa(String nombre) {
		return new CondicionComparativa(nombre,null,null);
	}
	
	public CondicionTaxativa crearCondicionTaxativa(String nombre){
		return new CondicionTaxativa(nombre, null, null, null);
	}
	
	public void agregarCondicion(Condicion condicion){
		repositorio.agregarCondicion(condicion);
	}
	
	public void agregarCondicionComparativa(String nombre){
		agregarCondicion(crearCondicionComparativa(nombre));
	}
	
	public void agregarCondicionTaxativa(String nombre){
		agregarCondicion(crearCondicionTaxativa(nombre));
	}
	
	public void comprobarLaPrimeraCondicion(String nombre) {
		assertEquals(repositorio.getCondicionesCargadas().get(0).getNombre(), nombre);
	}
	
	public void laCantidadDeCondicionesCargadasEs(Integer cantidad) {
		assertEquals(repositorio.cantidadDeCondiciones(), cantidad);
	}
	
	public void eliminarCondicion(String nombre) {
		repositorio.eliminarCondicion(nombre);
	}
	
	public void buscarCondicion(String nombre){
		assertEquals(repositorio.buscarCondicion(nombre).get().getNombre(), nombre);
	}
	
	public void verificarExistencia(String nombre, Boolean existe){
		assertEquals(repositorio.existeLaCondicion(nombre), existe);
	}

	
	@Before
	public void init(){
		repositorio = new RepositorioCondiciones();
	}
	
	@Test
	public void testAgregaUnaCondicionTaxativa(){
		agregarCondicionTaxativa("LaTaxa");
		comprobarLaPrimeraCondicion("LaTaxa");
	}
	
	@Test
	public void testAgregaUnaCondicionComparativa(){
		agregarCondicionComparativa("LaCompa");
		comprobarLaPrimeraCondicion("LaCompa");
	}
	
	@Test(expected = RuntimeException.class)
	public void testAgregaDosVecesLaMismaCondicionFalla(){
		agregarCondicionTaxativa("soy la misma");
		agregarCondicionTaxativa("soy la misma");
	}
	
	@Test
	public void testVerificaQueExisteUnaCondicion(){
		agregarCondicionComparativa("existe");
		verificarExistencia("existe", true);
	}
	
	@Test
	public void testVerificaQueNoExisteUnaCondicion(){
		verificarExistencia("no existe", false);
	}
	
	@Test
	public void testBuscaUnaCondicionDeFormaSatisfactoria(){
		agregarCondicionComparativa("el buscado");
		buscarCondicion("el buscado");
	}
	
	@Test
	public void testAgregaUnaCondicionPredeterminada(){
		agregarCondicion(new TEmpresaMas10Años());
		buscarCondicion("Empresa de mas de 10 años");
	}
	
	@Test(expected = NoSePuedeBorrarUnPredeterminadoException.class)
	public void testEliminarCondicionPredeterminadaFalla(){
		agregarCondicion(new TEmpresaMas10Años());
		eliminarCondicion("Empresa de mas de 10 años");
	}
	
	@Test(expected = RuntimeException.class)
	public void testBuscaUnaCondicionInexistenteFalla(){
		buscarCondicion("no existo");
	}

	@Test(expected = RuntimeException.class)
	public void testIntentarBorrarUnaCondicionInexistenteFalla() {
		eliminarCondicion("Empresa de mas de 10 años");
	}
	
	@Test
	public void testSePuedeEliminarUnaCondicionCustom() {
		agregarCondicionComparativa("pepito");
		eliminarCondicion("pepito");
		laCantidadDeCondicionesCargadasEs(0);
	}
	
	@Test
	public void testFiltraCondicionesTaxativas(){
		agregarCondicionTaxativa("taxativa");
		agregarCondicionComparativa("comparativa");
		agregarCondicionComparativa("comparativa2");
		assertEquals(repositorio.getCondicionesTaxativas().size(),1);
	}
	
	@Test
	public void testFiltraCondicionesComparativas(){
		agregarCondicionTaxativa("taxativa");
		agregarCondicionComparativa("comparativa");
		agregarCondicionComparativa("comparativa2");
		assertEquals(repositorio.getCondicionesComparativas().size(),2);
	}
	
}
