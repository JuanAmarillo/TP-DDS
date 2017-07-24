package unitTests;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import domain.Empresa;
import domain.indicadores.Indicador;
import domain.indicadores.calculoIndicadores.Calculable;
import domain.indicadores.calculoIndicadores.ConstructoresIndicador.Compilador;
import domain.repositorios.RepositorioIndicadores;
import ui.vm.VmUtils;

public class CompiladorTest {

	public Calculable compilado;
	public Empresa empresaMockeada;


	public CompiladorTest compilar(String... tokens) {
		compilado = new Compilador().compilar(VmUtils.convertirALista(tokens));
		return this;
	}

	public void obtiene(Double resultado) {
		assertEquals(compilado.calcularValor(empresaMockeada, null), resultado);
	}

	public void mockearCalculoIndicador() {
		Indicador indicadorMockeado = mockearIndicador();
		mockearRepositorioIndicador(indicadorMockeado);
	}

	public void mockearRepositorioIndicador(Indicador indicadorMockeado) {
		RepositorioIndicadores instance = mock(RepositorioIndicadores.class);
		when(instance.contieneElIndicador("indicador")).thenReturn(true);
		when(instance.contieneElIndicador("cuenta")).thenReturn(false);
		when(instance.buscarIndicador("indicador")).thenReturn(Optional.of(indicadorMockeado));
		RepositorioIndicadores.setInstance(instance);

	}

	public Indicador mockearIndicador() {
		Indicador indicadorMockeado = mock(Indicador.class);
		when(indicadorMockeado.calcularIndicador(empresaMockeada, null)).thenReturn(4.0);
		return indicadorMockeado;
	}

	public void mockearEmpresa() {
		empresaMockeada = mock(Empresa.class);
		when(empresaMockeada.getValorDeLaCuenta("cuenta", null)).thenReturn(5.0);
		when(empresaMockeada.getValorDeLaCuenta("NoExiste", null)).thenThrow(new RuntimeException());
	}

	@Before
	public void init() {
		mockearEmpresa();
		mockearCalculoIndicador();
	}

	@Test
	public void compilaSumaTest() {
		compilar("2", "+", "2", "+", "5").obtiene(9.0);
	}

	@Test
	public void compilaRestaTest() {
		compilar("3", "-", "7", "-", "1").obtiene(-5.0);
	}

	@Test
	public void compilaMultiplicacionTest() {
		compilar("3", "*", "7", "*", "2").obtiene(42.0);
	}

	@Test
	public void respetaPrioridadDeMultiplicacionTest() {
		compilar("2", "+", "3", "*", "5").obtiene(17.0);
	}

	@Test
	public void compilaDivisionTest() {
		compilar("6", "/", "3", "/", "2").obtiene(1.0);
	}

	@Test
	public void respetaPrioridadDeDivisionTest() {
		compilar("2", "-", "10", "/", "5").obtiene(0.0);
	}

	@Test
	public void respetaPrioridadDeLosParetensisTest() {
		compilar("(", "2", "+", "3", ")", "*", "4").obtiene(20.0);
	}

	@Test(expected = RuntimeException.class)
	public void compilarPotenciacionFallaTest() {
		compilar("2", "^", "3");
	}

	@Test(expected = RuntimeException.class)
	public void compilarUnaExpresionQueEmpiezaConOperadorFalla() {
		compilar("*", "5");
	}

	@Test(expected = RuntimeException.class)
	public void compilarUnaExpresionQueTerminaConOperadorFalla() {
		compilar("6", "*");
	}

	@Test(expected = RuntimeException.class)
	public void compilarUnaExpresionQueLeFaltaParentesisFalla() {
		compilar("(", "2", "+", "3");
	}

	@Test
	public void compilaIndicadoresTest() {
		compilar("indicador", "+", "5").obtiene(9.0);
	}

	@Test
	public void compilaCuentasTest() {
		compilar("cuenta", "-", "2").obtiene(3.0);
	}

	@Test(expected = RuntimeException.class)
	public void cuentaNoExistenteRompeTest() {
		compilar("NoExiste").obtiene(0.0);
	}
	
	@Test
	public void operacionCombinadaTest() {
		compilar("20", "/", "(", "3", "*", "4", "-", "2", ")", "+", "indicador", "-", "cuenta").obtiene(1.0);
	}

}
