package unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Stream;

import org.javatuples.Pair;
import org.junit.Test;

import domain.Empresa;
import domain.metodologias.EmpresasAPesar;
import mocks.PreparadorDeEmpresas;

public class EmpresasAPesarTest {
	List<Empresa> empresas = PreparadorDeEmpresas.prepararEmpresas();
	Empresa empresa = empresas.get(0);

	EmpresasAPesar empresasAPesar = new EmpresasAPesar(empresas, 15.0);

	Pair<Empresa, Double> emprConPeso1 = Pair.with(empresas.get(0), 7.0);

	Pair<Empresa, Double> emprConPeso2 = Pair.with(empresas.get(1), 9.5);

	Pair<Empresa, Double> emprConPeso3 = Pair.with(empresas.get(0), 8.0);

	Stream<Pair<Empresa, Double>> emprsConPeso = Stream.of(emprConPeso1, emprConPeso2, emprConPeso3);

	@Test
	public void daPesoYOrdenaTest() {
		List<Pair<Empresa, Double>> emprs = empresasAPesar.darPeso();
		assertEquals(emprs.size(), 5);
	}

	@Test
	public void ordenarPorPesoTest() {
		List<Pair<Empresa, Double>> emprConPesoOrdenadas = empresasAPesar.ordenarPorPeso(emprsConPeso);
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