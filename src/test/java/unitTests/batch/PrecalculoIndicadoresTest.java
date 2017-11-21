package unitTests.batch;



import static org.junit.Assert.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import batchProccessing.ContenedorValoresARecalcular;
import domain.Cuenta;
import domain.Empresa;

public class PrecalculoIndicadoresTest {

	private Empresa empresa;
	
	@Before
	public void antes() {
		empresa = new Empresa("Manaos");
		Cuenta cuenta = new Cuenta("MejorGaseosa", "Siempre", 99999.0);
		Cuenta cuenta2 = new Cuenta("PeorGaseosa", "Nunca", 1000.0);
		empresa.setCuentas(Stream.of(cuenta, cuenta2).collect(Collectors.toSet()));
		ContenedorValoresARecalcular.cargarNuevaInstancia();
	}
	
	@Test
	public void actualizarUnaCuentaAgregaAlContenedor() {
		Cuenta cuenta = empresa.buscarCuentaDe("MejorGaseosa", "Siempre").get();
		empresa.actualizarValores(cuenta, new Cuenta("MejorGaseosa", "Siempre", 1000.0));
		assertEquals(1, ContenedorValoresARecalcular.instance().cantidadDeValoresARecalcular());
	}
}
