package unitTests.condiciones;

import static org.junit.Assert.assertEquals;
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
import domain.repositorios.RepositorioCondiciones;
import mocks.IndicadorNoCalculableMock;
import unitTests.fixtureEmpresas.PreparadorDeEmpresas;

public class CondicionTest {

	List<Empresa> empresas;

	private List<EmpresaConPeso> aplicarCondicionALista(Condicion condicion) {
		List<EmpresaConPeso> listaEmpresas = empresas.stream().map(empresa -> new EmpresaConPeso(empresa, 0.0))
				.collect(Collectors.toList());
		listaEmpresas = condicion.aplicarCondicionEnPeriodo(listaEmpresas, "pascuas");
		return listaEmpresas;
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
