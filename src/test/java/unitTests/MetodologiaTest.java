package unitTests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import domain.Empresa;
import domain.condiciones.Condicion;
import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionTaxativa;
import domain.condiciones.condicionesPredeterminadas.CEmpresaMayorAntiguedad;
import domain.condiciones.condicionesPredeterminadas.CEndeudamiento;
import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;
import domain.metodologias.Metodologia;
import mocks.PreparadorDeEmpresas;

public class MetodologiaTest {

	Condicion condicionTAntiguedad =  new CondicionTaxativa(new TEmpresaMas10Años().getNombre(),new TEmpresaMas10Años().getIndicador(), new TEmpresaMas10Años().getOperador() , 0.0);
	Condicion condicionCEndeudamiento = new CondicionComparativa(new CEndeudamiento().getNombre(), new CEndeudamiento().getIndicador(), new CEndeudamiento().getOperador(), 5.0);
	Condicion condicionCAntiguedad = new CondicionComparativa(new CEmpresaMayorAntiguedad().getNombre(), new CEmpresaMayorAntiguedad().getIndicador(), new CEmpresaMayorAntiguedad().getOperador(), 10.0);

	List<Condicion> condicionesMixtas = Arrays.asList(condicionTAntiguedad, condicionCEndeudamiento);
	List<Condicion> condicionesTaxativas = Arrays.asList(condicionTAntiguedad);
	List<Condicion> condicionesComparativas = Arrays.asList(condicionCAntiguedad,
			condicionCEndeudamiento);

	Metodologia metodologiaMixta = new Metodologia("PepitaMixta", condicionesMixtas);
	Metodologia metodologiaTaxativa = new Metodologia("PepitaT", condicionesTaxativas);
	Metodologia metodologiaComparativa = new Metodologia("PepitaC", condicionesComparativas);

	List<Empresa> listaEmpresas = PreparadorDeEmpresas.prepararEmpresas();

	private void imprimirNombres(List<Empresa> listaResultante) {
		listaResultante.stream().forEach(empresa -> System.out.println(empresa.getNombre()));
		System.out.println("...");
	}
/*
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
*/
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