package unitTests.condiciones;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import domain.Empresa;
import domain.condiciones.CondicionComparativa;
import domain.condiciones.condicionesPredeterminadas.CEmpresaMayorAntiguedad;
import domain.condiciones.condicionesPredeterminadas.CEndeudamiento;
import domain.metodologias.EmpresaConPeso;
import unitTests.fixtureEmpresas.PreparadorDeEmpresas;

public class CondicionComparativaTest {

	CondicionComparativa condicionCEndeudamiento = new CEndeudamiento();
	CondicionComparativa condicionCAntiguedad = new CEmpresaMayorAntiguedad();

	List<Empresa> listaEmpresas = PreparadorDeEmpresas.prepararEmpresas();

	List<EmpresaConPeso> listaEmpresasInicializadas = listaEmpresas.stream()
			.map(empresa -> new EmpresaConPeso(empresa, 0.0)).collect(Collectors.toList());

	Empresa cocaCola = listaEmpresas.get(0);
	Empresa sorny = listaEmpresas.get(1);

	private void imprimirNombres(List<Empresa> listaResultante) {
		listaResultante.stream().forEach(empresa -> System.out.println(empresa.getNombre()));
		System.out.println("...");
	}

	private List<Empresa> convertirAEmpresas(List<EmpresaConPeso> listaAConvertir) {
		return listaAConvertir.stream().map(empresaConPeso -> empresaConPeso.getEmpresa()).collect(Collectors.toList());
	}

	@Test
	public void testEvaluaPorEndeudamiento() {
		List<EmpresaConPeso> empresasOrdenadas = condicionCEndeudamiento
				.aplicarCondicionEnPeriodo(listaEmpresasInicializadas, "pascuas");
		List<Empresa> empresas = this.convertirAEmpresas(empresasOrdenadas);
		imprimirNombres(empresas);
		assertEquals(empresas.get(0), listaEmpresas.get(1));
		assertEquals(empresas.get(1), listaEmpresas.get(3));
		assertEquals(empresas.get(2), listaEmpresas.get(0));
		assertEquals(empresas.get(3), listaEmpresas.get(4));
		assertEquals(empresas.get(4), listaEmpresas.get(2));
	}

	@Test
	public void testEvaluaPorAntiguedad() {
		List<EmpresaConPeso> empresasOrdenadas = condicionCAntiguedad
				.aplicarCondicionEnPeriodo(listaEmpresasInicializadas, "pascuas");
		List<Empresa> empresas = this.convertirAEmpresas(empresasOrdenadas);
		imprimirNombres(empresas);
		assertEquals(empresas.get(0), listaEmpresas.get(0));
		assertEquals(empresas.get(1), listaEmpresas.get(3));
		assertEquals(empresas.get(2), listaEmpresas.get(2));
		assertEquals(empresas.get(3), listaEmpresas.get(1));
		assertEquals(empresas.get(4), listaEmpresas.get(4));
	}

	@Test
	public void testEvaluaCocaColaYSornyPorEndeudamiento() {
		Integer resultado = condicionCEndeudamiento.evaluarCondicionEnPeriodo(cocaCola, sorny, "pascuas");
		assertTrue(resultado > 0);
	}

	@Test
	public void testEvaluaCocaColaYSornyPorAntiguedad() {
		Integer resultado = condicionCAntiguedad.evaluarCondicionEnPeriodo(sorny, cocaCola, "pascuas");
		assertTrue(resultado > 0);
	}
}