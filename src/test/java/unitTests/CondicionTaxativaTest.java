package unitTests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import domain.Empresa;
import domain.condiciones.CondicionTaxativa;

public class CondicionTaxativaTest extends CondicionTest<CondicionTaxativa> {
	
	public void crearCondicion() {
		condicion = new CondicionTaxativa("condicion", mockearIndicador(), mockearOperador(), 15.0);
	}
	
	public void evaluarCondicion(Empresa empresa,Boolean resultado){
		assertEquals(condicion.evaluarCondicionEnPeriodo(empresa, "2017"),resultado);
	}
	
	@Before
	public void init(){
		mockearEmpresas();
		crearCondicion();
	}

	@Test
	public void testLaEmpresaUnoEvaluadaNoPasa(){
		evaluarCondicion(empresaUno, false);
	}
	
	@Test
	public void testLaEmpresaDosEvaluadaPasa(){
		evaluarCondicion(empresaDos, true);
	}
	
	@Test
	public void testAplicaLaCondicionALaEmpresaUnoYNoPasa(){
		aplicarCondicion(empresaUno);
		verificarTamano(0);
	}
	
	@Test
	public void testAplicaLaCondicionALaEmpresaDosYPasa(){
		aplicarCondicion(empresaDos);
		verificarTamano(1);
	}
	
	@Test
	public void testAplicarLaCondicionAAmbasEmpresasYSoloQuedaLaEmpresaDos(){
		aplicarCondicion(empresaUno,empresaDos);
		verificarEmpresa(0, empresaDos);
		verificarTamano(1);
	}
	
	
}
