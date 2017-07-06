package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Empresa;
import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionTaxativa;
import domain.condiciones.condicionesPredeterminadas.CEmpresaMayorAntiguedad;
import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;
import domain.metodologias.AplicadorDeCondicionesComparativas;
import domain.metodologias.AplicadorDeCondicionesTaxativas;
import domain.metodologias.ListaMetodologia;

public class MetodologiasTest {

	List<Empresa> empresas;
	
	@Before
	public void init() {
		empresas = PreparadorDeEmpresas.prepararEmpresas();
	}
	
	@Test
	public void testAplicarMetodologiaSimpleTaxativa() {
		CondicionTaxativa condicion = new TEmpresaMas10Años();
		ListaMetodologia lista = new ListaMetodologia();
		AplicadorDeCondicionesTaxativas ap = new AplicadorDeCondicionesTaxativas(lista, condicion);
		List<Empresa> listaResultante = ap.aplicarMetodologia(empresas, "pascuas");
		assertEquals(2, listaResultante.size());
	}
	
	@Test
	public void testAplicarMetodologiaSimpleComparativa() {
		CondicionComparativa condicion = new CEmpresaMayorAntiguedad();
		ListaMetodologia lista = new ListaMetodologia();
		AplicadorDeCondicionesComparativas ap = new AplicadorDeCondicionesComparativas(lista,condicion);
		List<Empresa> listaResultante = ap.aplicarMetodologia(empresas, "pascuas");
		System.out.println(listaResultante.size());
		assertTrue(listaResultante.get(0).esLaMismaEmpresaQue(new Empresa().setNombre("Coca-Cola")));
	}
}
