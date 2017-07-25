package integrationTest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import domain.Empresa;
import domain.condiciones.condicionesPredeterminadas.CEmpresaMayorAntiguedad;
import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;
import domain.metodologias.AplicaMetodologia;
import domain.metodologias.Metodologia;
import mocks.PreparadorDeEmpresas;

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
	
	private void asertarCantidad(List<Empresa> listaResultante, int cantidad) {
		assertEquals(cantidad, listaResultante.size());
	}
	
	 //     REWORK IN PROGRESS
	
	@Test
	public void testAplicarMetodologiaSimpleTaxativa() {
		Metodologia met = new Metodologia("Pepita", Arrays.asList(new TEmpresaMas10Años()), Arrays.asList());
		List<Empresa> empresasFiltradas = new AplicaMetodologia(empresas).aplicarMetodologia(met, "pascuas");
		asertarCantidad(empresasFiltradas, 2);
	}
	
	@Test
	public void testAplicarMetodologiaSimpleComparativa() {
		Metodologia met = new Metodologia("Pepita", Arrays.asList(), Arrays.asList(new CEmpresaMayorAntiguedad().setPeso(10.0)));
		List<Empresa> empresasComparadas = new AplicaMetodologia(empresas).aplicarMetodologia(met, "pascuas");
		imprimirNombres(empresasComparadas);
		asertarEmpresa(empresasComparadas, 0, "Coca-Cola");
		asertarEmpresa(empresasComparadas, 1, "Pepsi-Co");
	}
/*
	
	
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

	*/
}
