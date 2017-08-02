package unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Empresa;
import domain.condiciones.CondicionTaxativa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;

public class CondicionTaxativaTest extends CondicionTest<CondicionTaxativa> {
	
	public void crearCondicion(Indicador indicadorMock, OperadorCondicion operadorMock) {
		condicion = new CondicionTaxativa("condicion", indicadorMock, operadorMock, 15.0);
	}
	
	public void evaluarCondicion(Empresa empresa,Boolean resultado){
		assertEquals(condicion.evaluarCondicion(empresa, "2017"),resultado);
	}
	
	@Before
	public void init(){
		mockearEmpresas();
		crearCondicion(mockearIndicador(),mockearOperador());
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
