package unitTests.empresas;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import domain.Cuenta;
import domain.Empresa;
import unitTests.TestUtils;

public class EmpresaTest {

	private Empresa empresa;

	public Set<Cuenta> generarCuentas(String... nombres) {
		return TestUtils.convertirALista(nombres).stream().map(nombre -> crearCuenta(nombre)).collect(Collectors.toSet());
	}

	public Cuenta crearCuenta(String nombre) {
		return new Cuenta(nombre, "2017", 4.0);
	}

	public void agregar(String... nombres) {
		empresa.agregarCuentas(generarCuentas(nombres));
	}

	public void laCantidadDeCuentasEs(int cantidad) {
		assertEquals(empresa.getCuentas().size(), cantidad);
	}

	public void laCantidadDeCuentasDel(String periodo, int cantidad) {
		assertEquals(empresa.getCuentasSegun(periodo).size(), cantidad);
	}

	public void contieneCuenta(String cuenta, Boolean loContiene) {
		assertEquals(contieneLaCuenta(cuenta), loContiene);
	}

	public boolean contieneLaCuenta(String cuenta) {
		return empresa.contieneLaCuenta(crearCuenta(cuenta));
	}

	public void valorDeLaCuenta(String cuenta, String periodo, Double valor) {
		assertEquals(valorDe(periodo, cuenta), valor);
	}

	public Double valorDe(String periodo, String cuenta) {
		return empresa.getValorDeLaCuenta(cuenta, periodo);
	}

	@Before
	public void init() {
		empresa = new Empresa("Manaos");
		empresa.setCuentas(generarCuentas("activo", "pasivo", "caja y bancos"));
	}

	@Test
	public void testContieneElActivo() {
		contieneCuenta("activo", true);
	}

	@Test
	public void testNoContieneDeudas() {
		contieneCuenta("deudas", false);
	}

	@Test
	public void testLasCuentasDel2017SonTres() {
		laCantidadDeCuentasDel("2017", 3);
	}

	@Test
	public void testNoHayCuentasDel2016() {
		laCantidadDeCuentasDel("2016", 0);
	}

	@Test
	public void testAgregarNuevasCuentas() {
		agregar("hola");
		laCantidadDeCuentasEs(4);
	}

	@Test
	public void testNoPuedeAgregarCuentasExistente() {
		agregar("activo", "pasivo");
		laCantidadDeCuentasEs(3);
	}

	@Test
	public void testElValorDelActivoDel2017EsCuatro() {
		valorDeLaCuenta("activo", "2017", 4.0);
	}

	@Test(expected = RuntimeException.class)
	public void testElValorDelActivoEn2016NoExiste() {
		valorDeLaCuenta("activo", "2016", 4.0);
	}

}
