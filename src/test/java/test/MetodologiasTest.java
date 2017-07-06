package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Empresa;
import domain.condiciones.CondicionTaxativa;
import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;
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
}
