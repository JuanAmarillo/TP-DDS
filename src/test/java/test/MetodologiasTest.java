package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Empresa;
import domain.condiciones.Condicion;
import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionTaxativa;
import domain.condiciones.OperadoresCondicion.Menor;
import domain.condiciones.condicionesPredeterminadas.CEmpresaMayorAntiguedad;
import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;
import domain.metodologias.AplicadorDeCondiciones;
import domain.metodologias.ListaMetodologia;
import domain.metodologias.Metodologia;

public class MetodologiasTest {

	List<Empresa> empresas;
	
	@Before
	public void init() {
		empresas = PreparadorDeEmpresas.prepararEmpresas();
	}
	
	private void asertarEmpresa(List<Empresa> listaResultante, int posicion, String nombre) {
		assertTrue(listaResultante.get(posicion).esLaMismaEmpresaQue(new Empresa().setNombre(nombre)));
	}
	
	private void imprimirNombres(List<Empresa> listaResultante) {
		listaResultante.stream().forEach(empresa -> System.out.println(empresa.getNombre()));		
	}
	
	public AplicadorDeCondiciones prepararTaxativa(Metodologia superior, CondicionTaxativa condicion) {
		return new AplicadorDeCondiciones(superior, condicion);
	}
	
	public AplicadorDeCondiciones prepararComparativa(Metodologia superior, CondicionComparativa condicion) {
		return new AplicadorDeCondiciones(superior,condicion);
	}
	
	@Test
	public void testAplicarMetodologiaSimpleTaxativa() {
		List<Empresa> listaResultante =  prepararTaxativa(new ListaMetodologia(), new TEmpresaMas10Años()).aplicarMetodologia(empresas, "pascuas");
		asertarCantidad(listaResultante, 2);
	}

	private void asertarCantidad(List<Empresa> listaResultante, int cantidad) {
		assertEquals(cantidad, listaResultante.size());
	}
	
	@Test
	public void testAplicarMetodologiaSimpleComparativa() {
		List<Empresa> listaResultante = prepararComparativa(new ListaMetodologia(), new CEmpresaMayorAntiguedad()).aplicarMetodologia(empresas, "pascuas");
		asertarEmpresa(listaResultante, 0, "Coca-Cola");
		asertarEmpresa(listaResultante, 1, "Pepsi-Co");
	}
	
	@Test
	public void testOrdenaEnOrdenInverso() {
		CondicionComparativa condicion = new CEmpresaMayorAntiguedad();
		condicion.setOperador(new Menor());
		List<Empresa> listaResultante = prepararComparativa(new ListaMetodologia(), condicion).aplicarMetodologia(empresas, "pascuas");
		asertarEmpresa(listaResultante, 0, "Panaphonics");
		asertarEmpresa(listaResultante, 1, "Sorny");
	}
	
	@Test
	public void testAplicarMetodologiaMixtaAntiguedad() {
		AplicadorDeCondiciones apt = prepararTaxativa(new ListaMetodologia(), new TEmpresaMas10Años());
		AplicadorDeCondiciones apc = prepararComparativa(apt, new CEmpresaMayorAntiguedad());
		List<Empresa> listaResultante = apc.aplicarMetodologia(empresas, "pascuas");
		asertarCantidad(listaResultante, 2);
		asertarEmpresa(listaResultante, 0, "Coca-Cola");
		asertarEmpresa(listaResultante, 1, "Pepsi-Co");
	}

	

	
}
