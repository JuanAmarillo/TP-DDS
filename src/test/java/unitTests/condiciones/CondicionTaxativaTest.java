package unitTests.condiciones;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import domain.Empresa;
import domain.condiciones.CondicionTaxativa;
import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;
import domain.condiciones.condicionesPredeterminadas.TEndeudamiento;
import domain.metodologias.EmpresaConPeso;
import unitTests.fixtureEmpresas.PreparadorDeEmpresas;

public class CondicionTaxativaTest{

	CondicionTaxativa condicionTEmpresaMas10Años = new TEmpresaMas10Años();
	CondicionTaxativa condicionTEndeudamiento = new TEndeudamiento();
	
	List<Empresa> listaEmpresas = PreparadorDeEmpresas.prepararEmpresas();

	List<EmpresaConPeso> listaEmpresasInicializadas = listaEmpresas.stream().map(empresa -> new EmpresaConPeso(empresa, 0.0))
			.collect(Collectors.toList());
	
	Empresa cocaCola = listaEmpresas.get(0);
	Empresa magnetBox = listaEmpresas.get(2);
	Empresa pepsiCo = listaEmpresas.get(3);
	Empresa panaphonics = listaEmpresas.get(4);
	
	private void imprimirNombres(List<Empresa> listaResultante) {
		listaResultante.stream().forEach(empresa -> System.out.println(empresa.getNombre()));
		System.out.println("...");
	}
	
	private List<Empresa> convertirAEmpresas(List<EmpresaConPeso> listaAConvertir){
		return listaAConvertir.stream().map(empresaConPeso->empresaConPeso.getEmpresa()).collect(Collectors.toList());
	}
	
	@Test
	public void testEvaluaPorEmpresaMas10Años(){
		List<EmpresaConPeso> empresasFiltradas = condicionTEmpresaMas10Años.aplicarCondicionEnPeriodo(listaEmpresasInicializadas, "pascuas");
		assertEquals(empresasFiltradas.size(),2);
		List<Empresa> empresas = convertirAEmpresas(empresasFiltradas);
		imprimirNombres(empresas);
		assertTrue(empresas.contains(cocaCola));
		assertTrue(empresas.contains(pepsiCo));
		
	}
	
	@Test
	public void testEvaluaPorEndeudamiento(){
		List<EmpresaConPeso> empresasFiltradas = condicionTEndeudamiento.aplicarCondicionEnPeriodo(listaEmpresasInicializadas, "pascuas");
		assertEquals(empresasFiltradas.size(),4);
		List<Empresa> empresas = convertirAEmpresas(empresasFiltradas);
		imprimirNombres(empresas);
		assertTrue(empresas.contains(cocaCola));
		assertTrue(empresas.contains(pepsiCo));
		assertTrue(empresas.contains(magnetBox));
		assertTrue(empresas.contains(panaphonics));
	}
}
