package unitTests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import domain.Empresa;
import domain.condiciones.CondicionComparativa;

public class CondicionComparativaTest extends CondicionTest<CondicionComparativa> {
	
	public void crearCondicion(){
		condicion = new CondicionComparativa("condicion", mockearIndicador(), mockearOperador());
	}
	
	public void evaluarCondicion(Empresa empresaUno,Empresa empresaDos,Integer resultado){
		assertEquals(condicion.evaluarCondicion(empresaUno,empresaDos, "2017"),resultado);
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
		aplicarCondicion(empresaUno,empresaDos);
		verificarEmpresa(0, empresaDos);
		verificarEmpresa(1, empresaUno);
	}
	
	@Test
	public void testAplicarCondicionesDesdeEmpresaDosHastaEmpresaUnoQuedaComoEsta(){
		aplicarCondicion(empresaDos,empresaUno);
		verificarEmpresa(0, empresaDos);
		verificarEmpresa(1, empresaUno);
	}
}
