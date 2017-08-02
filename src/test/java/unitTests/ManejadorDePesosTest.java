package unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import domain.Empresa;
import domain.condiciones.ManejadorDePesos;
import domain.metodologias.EmpresaEnCalculo;

public class ManejadorDePesosTest {

	private ManejadorDePesos manejadorDePesos;
	private List<EmpresaEnCalculo> empresasEnCalculo;

	public List<String> list(String... nombresEmpresas) {
		return Arrays.asList(nombresEmpresas);
	}

	public List<Empresa> crearEmpresas(String... nombresEmpresas) {
		return list(nombresEmpresas).stream().map(nombreEmpresa -> crearEmpresa(nombreEmpresa))
				.collect(Collectors.toList());
	}

	private Empresa crearEmpresa(String nombreEmpresa) {
		return new Empresa(nombreEmpresa);
	}

	public void agregarPesosA(String... nombresEmpresas) {
		empresasEnCalculo = manejadorDePesos.agregarPeso(crearEmpresas(nombresEmpresas));
	}
	
	public void verificarPeso(Integer indice, Double peso){
		assertEquals(empresasEnCalculo.get(indice).getPeso(), peso);
	}

	@Before
	public void init() {
		manejadorDePesos  = new ManejadorDePesos(10.0);
		empresasEnCalculo = null;
	}

	@Test
	public void testAgregaPesosAUnaListaDeUnaEmpresa() {
		agregarPesosA("unaEmpresa");
		verificarPeso(0, 10.0);
	}
	
	@Test
	public void testAgregaPesosAUnaListaDeTresEmpresas() {
		agregarPesosA("empresa1","empresa2","empresa3");
		verificarPeso(0, 30.0);
		verificarPeso(1, 20.0);
		verificarPeso(2, 10.0);
	}

}
