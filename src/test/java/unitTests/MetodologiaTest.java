package unitTests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import domain.Empresa;
import domain.condiciones.CondicionAplicable;
import domain.condiciones.condicionesPredeterminadas.CEmpresaMayorAntiguedad;
import domain.condiciones.condicionesPredeterminadas.CEndeudamiento;
import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;
import domain.metodologias.Metodologia;
import mocks.PreparadorDeEmpresas;

public class MetodologiaTest {

	CondicionAplicable condicionTAntiguedad =  new CondicionAplicable(new TEmpresaMas10Años(), 0.0);
	CondicionAplicable condicionCEndeudamiento = new CondicionAplicable(new CEndeudamiento(), 5.0);
	CondicionAplicable condicionCAntiguedad = new CondicionAplicable(new CEmpresaMayorAntiguedad(), 10.0);

	List<CondicionAplicable> condicionesMixtas = Arrays.asList(condicionTAntiguedad, condicionCEndeudamiento);
	List<CondicionAplicable> condicionesTaxativas = Arrays.asList(condicionTAntiguedad);
	List<CondicionAplicable> condicionesComparativas = Arrays.asList(condicionCAntiguedad,
			condicionCEndeudamiento);

	Metodologia metodologiaMixta = new Metodologia("PepitaMixta", condicionesMixtas);
	Metodologia metodologiaTaxativa = new Metodologia("PepitaT", condicionesTaxativas);
	Metodologia metodologiaComparativa = new Metodologia("PepitaC", condicionesComparativas);

	List<Empresa> listaEmpresas = PreparadorDeEmpresas.prepararEmpresas();

	private void imprimirNombres(List<Empresa> listaResultante) {
		listaResultante.stream().forEach(empresa -> System.out.println(empresa.getNombre()));
		System.out.println("...");
	}

	@Test
	public void metodologiaAplicableFiltraPorCondicionTaxativaTest() {
		List<CondicionAplicable> condFiltradas = metodologiaMixta.obtenerCondicionesTaxativas();
		assertEquals(1, condFiltradas.size());
	}

	@Test
	public void metodologiaAplicableFiltraPorCondicionComparativaTest() {
		List<CondicionAplicable> condFiltradas = metodologiaMixta.obtenerCondicionesComparativas();
		assertEquals(1, condFiltradas.size());
	}

	@Test
	public void seAplicanCondicionesTaxativasTest() {
		List<Empresa> emprFiltradas = metodologiaMixta.aplicarCondicionesTaxativas(listaEmpresas);
		assertEquals(emprFiltradas.size(), 2);
	}

	@Test
	public void seAplicanCondicionesComparativasTest() {
		List<Empresa> emprFiltradas = metodologiaMixta.aplicarCondicionesComparativas(listaEmpresas);
		Empresa sorny = listaEmpresas.get(1);
		assertEquals(emprFiltradas.size(), listaEmpresas.size());
		assertEquals(emprFiltradas.get(0), sorny);
		imprimirNombres(emprFiltradas); // Por la condición endeudamiento
										// deberían quedar: Sorny, Pepsi-Co,
										// Coca-Cola, Panaphonics, MagnetBox
	}

	@Test
	public void seAplicanCondicionesTest() {
		List<Empresa> emprFiltradas = metodologiaMixta.aplicarCondiciones(listaEmpresas);
		Empresa pepsi = listaEmpresas.get(3);
		Empresa coca = listaEmpresas.get(0);
		imprimirNombres(emprFiltradas); // Debería quedar Pepsi-Co,Coca-Cola
		assertEquals(emprFiltradas.size(), 2);
		assertEquals(emprFiltradas.get(0), pepsi);
		assertEquals(emprFiltradas.get(1), coca);
	}

	@Test
	public void seAplicanSoloCondicionesTaxativasTest() {
		List<Empresa> emprFiltradas = metodologiaTaxativa.aplicarCondiciones(listaEmpresas);
		assertEquals(emprFiltradas.size(), 2);
	}

	@Test
	public void seAplicanSoloCondicionesComparativasTest() {
		List<Empresa> emprFiltradas = metodologiaComparativa.aplicarCondiciones(listaEmpresas);
		assertEquals(emprFiltradas.size(), 5);
		imprimirNombres(emprFiltradas);
	}

}