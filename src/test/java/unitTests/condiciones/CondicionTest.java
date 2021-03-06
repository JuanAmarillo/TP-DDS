package unitTests.condiciones;

import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import domain.Empresa;
import domain.condiciones.Condicion;
import domain.condiciones.CondicionTaxativa;
import domain.condiciones.condicionesPredeterminadas.CEmpresaMayorAntiguedad;
import domain.metodologias.EmpresaConPeso;
import mocks.IndicadorNoCalculableMock;
import unitTests.fixtureEmpresas.PreparadorDeEmpresas;

public class CondicionTest {

	List<Empresa> empresas;

	private List<EmpresaConPeso> aplicarCondicionALista(Condicion condicion) {
		List<EmpresaConPeso> listaEmpresas = empresasConPesos();
		return condicion.aplicarCondicionEnPeriodo(listaEmpresas, "pascuas");
	}

	public List<EmpresaConPeso> empresasConPesos() {
		return empresas.stream().map(empresa -> new EmpresaConPeso(empresa, 0.0))
				.collect(Collectors.toList());
	}

	@Before
	public void init() {
		empresas = PreparadorDeEmpresas.prepararEmpresas();
	}

	@Test
	public void testCondicionPredeterminadaValor() {
		CEmpresaMayorAntiguedad condicion = new CEmpresaMayorAntiguedad();
		assertFalse(condicion.esCustom());
	}

	@Test(expected = RuntimeException.class)
	public void testNoSePuedeCalcularCondicion() {
		CondicionTaxativa condicion = new CondicionTaxativa("Calculame esta", null, null, null);
		condicion.setIndicador(new IndicadorNoCalculableMock());
		aplicarCondicionALista(condicion);
	}
}
