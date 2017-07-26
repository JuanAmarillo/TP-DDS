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
import domain.condiciones.condicionesPredeterminadas.CEndeudamiento;
import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;
import domain.condiciones.condicionesPredeterminadas.TEndeudamiento;
import domain.indicadores.indicadoresPredeterminados.Endeudamiento;
import domain.metodologias.AplicaMetodologia;
import domain.metodologias.Metodologia;
import mocks.PreparadorDeEmpresas;

public class MetodologiasTest {

	List<Empresa> listaEmpresas;
	
	@Before
	public void init() {
		listaEmpresas = PreparadorDeEmpresas.prepararEmpresas();
	}
	
	private void asertarEmpresa(List<Empresa> listaResultante, int posicion, String nombre) {
		assertTrue(listaResultante.get(posicion).esLaMismaEmpresaQue(new Empresa(nombre)));
	}
	
	private void imprimirNombres(List<Empresa> listaResultante) {
		listaResultante.stream().forEach(empresa -> System.out.println(empresa.getNombre()));		
	}
	
	private void asertarCantidad(List<Empresa> listaResultante, int cantidad) {
		assertEquals(cantidad, listaResultante.size());
	}
	
	private List<Empresa> aplicarMetodologia(Metodologia met) {
		return new AplicaMetodologia(listaEmpresas).aplicarMetodologia(met, "pascuas").obtenerLista();
	}
	
	 //     REWORK IN PROGRESS
	
	@Test
	public void testSinCondicionesDevuelveLaMismaLista() {
		Metodologia met = new Metodologia("Pepita", Arrays.asList(), Arrays.asList());
		List<Empresa> empresasFiltradas = aplicarMetodologia(met);
		assertEquals(empresasFiltradas.size(), listaEmpresas.size());
	}
	
	@Test
	public void testAplicarMetodologiaSimpleTaxativa() {
		Metodologia met = new Metodologia("Pepita", Arrays.asList(new TEmpresaMas10Años()), Arrays.asList());
		List<Empresa> empresasFiltradas = aplicarMetodologia(met);
		asertarCantidad(empresasFiltradas, 2);
	}
	
	@Test
	public void testAplicarMetodologiaAntiguedad() {
		Metodologia met = new Metodologia("Pepita", Arrays.asList(), Arrays.asList(new CEmpresaMayorAntiguedad().setPeso(10.0)));
		List<Empresa> empresasComparadas = aplicarMetodologia(met);
		asertarEmpresa(empresasComparadas, 0, "Coca-Cola");
		asertarEmpresa(empresasComparadas, 1, "Pepsi-Co");
	}
	
	@Test
	public void testAplicarMetodologiaEndeudamiento() {
		Metodologia met = new Metodologia("Pepita", Arrays.asList(), Arrays.asList(new CEndeudamiento().setPeso(10.0)));
		List<Empresa> empresasComparadas = aplicarMetodologia(met);
		asertarEmpresa(empresasComparadas, 0, "Sorny");
		asertarEmpresa(empresasComparadas, 1, "Pepsi-Co");
	}
	
	@Test
	public void testAplicaUnaYUna() {
		Metodologia met = new Metodologia("Pepita", Arrays.asList(new TEmpresaMas10Años()), Arrays.asList(new CEmpresaMayorAntiguedad().setPeso(10.0)));
		List<Empresa> empresas = aplicarMetodologia(met);
		asertarCantidad(empresas,2);
		asertarEmpresa(empresas, 0, "Coca-Cola");
		asertarEmpresa(empresas, 1, "Pepsi-Co");
	}

	@Test
	public void testAplicaDosTaxativas() {
		Metodologia met = new Metodologia("Pepita", Arrays.asList(new TEmpresaMas10Años(), new TEndeudamiento() ), Arrays.asList());
		List<Empresa> empresas = aplicarMetodologia(met);
		asertarCantidad(empresas,1);
		asertarEmpresa(empresas,0,"Coca-Cola");
	}
	
	@Test
	public void testAplicaDosComparativas() { //La cuenta sobre este test se encuentra en el archivo Anexo.txt
		Metodologia met = new Metodologia("Pepita", Arrays.asList(),Arrays.asList(new CEmpresaMayorAntiguedad().setPeso(10.0), new CEndeudamiento().setPeso(12.0)));
		List<Empresa> empresas = aplicarMetodologia(met);
		asertarEmpresa(empresas, 0, "Pepsi-Co");
		asertarEmpresa(empresas, 1, "Coca-Cola");
		asertarEmpresa(empresas, 2, "Sorny");
		asertarEmpresa(empresas, 3, "MagnetBox");
		asertarEmpresa(empresas, 4, "Panaphonics");
	}
}
