package unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import domain.Empresa;
import domain.metodologias.EmpresaConPeso;
import domain.metodologias.EmpresasAPesar;
import mocks.PreparadorDeEmpresas;

public class EmpresasAPesarTest {
	List<Empresa> empresas = PreparadorDeEmpresas.prepararEmpresas();
	Empresa empresa = empresas.get(0);

	EmpresasAPesar empresasAPesar = new EmpresasAPesar(empresas, 15.0);

	EmpresaConPeso emprConPeso1 = new EmpresaConPeso(empresas.get(0),7.0);

	EmpresaConPeso emprConPeso2 = new EmpresaConPeso(empresas.get(1), 9.5);

	EmpresaConPeso emprConPeso3 = new EmpresaConPeso(empresas.get(2), 8.0);

	Stream<EmpresaConPeso> emprsConPeso = Stream.of(emprConPeso1, emprConPeso2, emprConPeso3);

	@Test
	public void daPesoYOrdenaTest() {
		List<EmpresaConPeso> empresasConPeso = empresasAPesar.darPesoYOrdenar();
		assertEquals(empresasConPeso.size(), 5);
	}

	@Test
	public void ordenarPorPesoTest() {
		List<EmpresaConPeso> emprConPesoOrdenadas = empresasAPesar.ordenarPorPeso(emprsConPeso);
		assertEquals(emprConPesoOrdenadas.size(), 3);
		assertEquals(emprConPesoOrdenadas.get(0), emprConPeso1);
		assertEquals(emprConPesoOrdenadas.get(1), emprConPeso3);
		assertEquals(emprConPesoOrdenadas.get(2), emprConPeso2);
	}

	@Test
	public void mayorPesoTest() {
		int resultado = empresasAPesar.mayorPeso(emprConPeso1, emprConPeso2);
		assertTrue(resultado < 0);
	}

	@Test
	public void darPesoTest() {
		Double resultado = empresasAPesar.darPeso(empresa);
		assertEquals(resultado, 75.0, 0.00001);
	}

}