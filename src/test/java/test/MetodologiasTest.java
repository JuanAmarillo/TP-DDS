package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Empresa;
import domain.condiciones.Condicion;
import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionTaxativa;
import domain.condiciones.condicionesPredeterminadas.CEmpresaMayorAntiguedad;
import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;
import domain.metodologias.AplicadorDeCondicionesComparativas;
import domain.metodologias.AplicadorDeCondicionesTaxativas;
import domain.metodologias.ListaMetodologia;
import domain.metodologias.Metodologia;

public class MetodologiasTest {

	List<Empresa> empresas;
	
	@Before
	public void init() {
		empresas = PreparadorDeEmpresas.prepararEmpresas();
	}
	
	private void imprimirNombres(List<Empresa> listaResultante) {
		listaResultante.stream().forEach(empresa -> System.out.println(empresa.getNombre()));		
	}
	
	public Metodologia prepararTaxativa(Metodologia superior, CondicionTaxativa condicion) {
		return new AplicadorDeCondicionesTaxativas(superior, condicion);
	}
	
	public Metodologia prepararComparativa(Metodologia superior, CondicionComparativa condicion) {
		return new AplicadorDeCondicionesComparativas(superior,condicion);
	}
	
	@Test
	public void testAplicarMetodologiaSimpleTaxativa() {
		List<Empresa> listaResultante =  prepararTaxativa(new ListaMetodologia(), new TEmpresaMas10Años()).aplicarMetodologia(empresas, "pascuas");
		assertEquals(2, listaResultante.size());
	}
	
	@Test
	public void testAplicarMetodologiaSimpleComparativa() {
		List<Empresa> listaResultante = prepararComparativa(new ListaMetodologia(), new CEmpresaMayorAntiguedad()).aplicarMetodologia(empresas, "pascuas");
		assertTrue(listaResultante.get(0).esLaMismaEmpresaQue(new Empresa().setNombre("Coca-Cola")));
		assertTrue(listaResultante.get(1).esLaMismaEmpresaQue(new Empresa().setNombre("Pepsi-Co")));
	}
	
	@Test
	public void testOrdenaEnOrdenInverso() {
		CondicionComparativa condicion = new CEmpresaMayorAntiguedad();
		condicion.setOperador("<");
		List<Empresa> listaResultante = prepararComparativa(new ListaMetodologia(), condicion).aplicarMetodologia(empresas, "pascuas");
		assertTrue(listaResultante.get(0).esLaMismaEmpresaQue(new Empresa().setNombre("Panaphonics")));
		assertTrue(listaResultante.get(1).esLaMismaEmpresaQue(new Empresa().setNombre("Sorny")));
	}

	
}
