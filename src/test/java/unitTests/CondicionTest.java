package unitTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import domain.Empresa;
import domain.condiciones.Condicion;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;
import domain.metodologias.EmpresaConPeso;

public class CondicionTest<T extends Condicion> {
	
	protected List<Empresa> empresas;
	protected Empresa empresaUno;
	protected Empresa empresaDos;
	protected T condicion;

	public Indicador mockearIndicador(){
		Indicador indicadorMock = mock(Indicador.class);
		when(indicadorMock.calcularIndicador(empresaUno, "2017")).thenReturn(10.0);
		when(indicadorMock.calcularIndicador(empresaDos, "2017")).thenReturn(20.0);
		return indicadorMock;
	}

	public void mockearEmpresas() {
		empresaUno = mock(Empresa.class);
		empresaDos = mock(Empresa.class);
	}
	
	public OperadorCondicion mockearOperador(){
		OperadorCondicion operadorMock = mock(OperadorCondicion.class);
		when(operadorMock.comparar(10.0, 15.0)).thenReturn(-1);
		when(operadorMock.comparar(20.0, 15.0)).thenReturn( 1);
		when(operadorMock.comparar(10.0, 20.0)).thenReturn(-1);
		when(operadorMock.comparar(20.0, 10.0)).thenReturn( 1);
		return operadorMock;
	}
	/*
	public void aplicarCondicion(Empresa...empresas){
		EmpresaConPeso empresaConPeso = new EmpresaConPeso(empresas, 0.0);
		empresasAplicadas = condicion.aplicarCondicionEnPeriodo(Arrays.asList(empresaConPeso), "2017");
	}
	*/
	
	public List<Empresa> aplicarCondicion(List<Empresa> empresas){
		List<EmpresaConPeso> empresasConPeso = empresas.stream().map(empresa->new EmpresaConPeso(empresa,0.0)).collect(Collectors.toList());
		List<EmpresaConPeso> emprs =condicion.aplicarCondicionEnPeriodo(empresasConPeso, "2017");
		return emprs.stream().map(emprConPeso->emprConPeso.getEmpresa()).collect(Collectors.toList());
	}
	
	public void verificarEmpresa(Integer indice, Empresa empresa){
		assertEquals(empresas.get(indice), empresa);
	}
	
	public void verificarTamano(int tamano){
		assertEquals(empresas.size(), tamano);
	}
}
