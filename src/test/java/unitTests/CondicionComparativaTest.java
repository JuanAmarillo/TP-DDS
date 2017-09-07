package unitTests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Empresa;
import domain.condiciones.CondicionComparativa;

public class CondicionComparativaTest extends CondicionTest<CondicionComparativa> {
	
	public void crearCondicion(){
		condicion = new CondicionComparativa("condicion", mockearIndicador(), mockearOperador(), 10.0);
	}
	
	public void evaluarCondicion(Empresa empresaUno,Empresa empresaDos,Integer resultado){
		assertEquals(condicion.evaluarCondicionEnPeriodo(empresaUno,empresaDos, "2017"),resultado);
	}
	
	@Before
	public void init(){
		mockearEmpresas();
		crearCondicion();
	}
	
	@Test
	public void testEvaluaLaEmpresaUnoContraLaEmpresaDos(){
		evaluarCondicion(empresaUno, empresaDos, 1);
	}
	
	@Test
	public void testEvaluaLaEmpresaDosContraLaEmpresaUno(){
		evaluarCondicion(empresaDos, empresaUno, -1);
	}
	
	@Test
	public void testAplicarCondicionDesdeEmpresaUnoHastaEmpresaDosCambiaElOrden(){
		List<Empresa> empresas = Arrays.asList(empresaUno, empresaDos);
		List<Empresa> empresasOrdenadas = aplicarCondicion(empresas);
		//verificarEmpresa(0, empresaDos);
		//verificarEmpresa(1, empresaUno);
		assertEquals(empresasOrdenadas.get(0),empresaDos);
		assertEquals(empresasOrdenadas.get(1),empresaUno);
	}
	
	@Test
	public void testAplicarCondicionesDesdeEmpresaDosHastaEmpresaUnoQuedaComoEsta(){
		List<Empresa> empresas = Arrays.asList(empresaDos, empresaUno);
		List<Empresa> empresasOrdenadas = aplicarCondicion(empresas);
		//verificarEmpresa(0, empresaDos);
		//verificarEmpresa(1, empresaUno);
		assertEquals(empresasOrdenadas.get(0),empresaUno);
		assertEquals(empresasOrdenadas.get(1),empresaDos);
	}
}
