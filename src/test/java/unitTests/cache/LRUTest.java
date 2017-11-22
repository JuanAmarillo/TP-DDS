
package unitTests.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.Test;

import cacheIndicadores.LRU;

public class LRUTest {
	private LRU<String> algoritmo = new LRU<String>();
	private LinkedHashMap<String, String> enCache;
	
	private void ingresar(String clave, String valor) {
		algoritmo.set(enCache, clave, valor);
	}
	
	private void huboAcesso(String clave,String valor) {
		algoritmo.huboAcceso(enCache, clave, valor);
	}
	
	public String claveEnIndex(int index) {
		return (String) enCache.keySet().toArray()[index];
	}
	
	private void verificaIndiceValor(int index, String valor) {
		assertEquals(enCache.get(claveEnIndex(index)), valor);
	}
	
	private void verificaClaveValor(String clave, String valorEsperado) {
		assertEquals(enCache.get(clave), valorEsperado);
	}
	
	private void noVerificaClaveValor(String clave, String valorEsperado) {
		assertNotEquals(enCache.get(clave), valorEsperado);
	}
	
	@Before
	public void before() {
		enCache = new LinkedHashMap<>();
		algoritmo.setCapacidad(10);
	}
	
	@Test
	public void ingresarUnElemento() {
		ingresar("hola","hola1");
		verificaClaveValor("hola", "hola1");
	}
	
	@Test
	public void ingresarDosElemento() {
		ingresar("hola","hola1");
		ingresar("hola2","hola2");
		verificaClaveValor("hola", "hola1");
		verificaClaveValor("hola2", "hola2");
	}
	
	@Test
	public void ingresoOnceElementosYElPrimeroSeBorraYElUltimoEntra() {
		ingresar("1","1");
		ingresar("2","2");
		ingresar("3","3");
		ingresar("4","4");
		ingresar("5","5");
		ingresar("6","6");
		ingresar("7","7");
		ingresar("8","8");
		ingresar("9","9");
		ingresar("10","10");
		ingresar("11","11");
		noVerificaClaveValor("1", "1");
		verificaClaveValor("11", "11");
	}
	
	@Test
	public void accedeAUnElementoYSeActualizaPosicion() {
		ingresar("1", "1");
		ingresar("2", "2");
		ingresar("3", "3");
		huboAcesso("2", "2");
		verificaIndiceValor(0, "1");
		verificaIndiceValor(1, "3");
		verificaIndiceValor(2, "2");
		
	}

}
