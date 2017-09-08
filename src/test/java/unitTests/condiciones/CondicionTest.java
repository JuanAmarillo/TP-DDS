package unitTests.condiciones;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import domain.Empresa;
import domain.condiciones.Condicion;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;

public class CondicionTest<T extends Condicion> {

	protected List<Empresa> empresasAplicadas;
	protected Empresa empresaUno;
	protected Empresa empresaDos;
	protected T condicion;

	public Indicador mockearIndicador() {
		Indicador indicadorMock = mock(Indicador.class);
		when(indicadorMock.calcularIndicador(empresaUno, "2017")).thenReturn(10.0);
		when(indicadorMock.calcularIndicador(empresaDos, "2017")).thenReturn(20.0);
		return indicadorMock;
	}

	public void mockearEmpresas() {
		empresaUno = mock(Empresa.class);
		empresaDos = mock(Empresa.class);
	}

	public OperadorCondicion mockearOperador() {
		OperadorCondicion operadorMock = mock(OperadorCondicion.class);
		when(operadorMock.comparar(10.0, 15.0)).thenReturn(-1);
		when(operadorMock.comparar(20.0, 15.0)).thenReturn(1);
		when(operadorMock.comparar(10.0, 20.0)).thenReturn(-1);
		when(operadorMock.comparar(20.0, 10.0)).thenReturn(1);
		return operadorMock;
	}

	public void aplicarCondicion(Empresa... empresas) {
		empresasAplicadas = condicion.aplicarCondicionEnPeriodo(Arrays.asList(empresas), "2017");
	}

	public void verificarEmpresa(Integer indice, Empresa empresa) {
		assertEquals(empresasAplicadas.get(indice), empresa);
	}

	public void verificarTamano(int tamano) {
		assertEquals(empresasAplicadas.size(), tamano);
	}
}