package unitTests.cargaDeArchivos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import domain.Cuenta;
import domain.Empresa;
import domain.repositorios.RepositorioEmpresas;
import persistencia.LevantaArchivoEmpresa;

public class CargaEmpresaTest extends AbstractPersistenceTest {

	public RepositorioEmpresas repositorio = new RepositorioEmpresas();
	public LevantaArchivoEmpresa levantaArchivo = new LevantaArchivoEmpresa("src/test/resources/UnicaEmpresa");
	public Empresa empresa = new Empresa();
	
	@Override
	public EntityManager entityManager() {
		return repositorio.getEntityManager();
	}
	
	@Before
	public void antes() throws IOException{
		/*empresa.setAnioFundacion(1995);
		empresa.setNombre("Coca-Colita");
		Cuenta cuenta = new Cuenta("cuentita", "2do semestre", 1995.0);
		empresa.agregarCuentas(Stream.of(cuenta).collect(Collectors.toSet()));
		levantaArchivo = Mockito.mock(LevantaArchivoEmpresa.class);
		Mockito.when(levantaArchivo.getEmpresaDelArchivo(Mockito.any())).thenReturn(empresa);*/
	}
	
	@Test
	public void seCargaElArchivo() {
		levantaArchivo.execute();
		assertEquals(1.0, cantidadEmpresasCargadas(), 0.0);
	}

	private Long cantidadEmpresasCargadas() {
		return RepositorioEmpresas.instance().cantidadElementosCargados();
	}
	
	@Test
	public void rompeConArchivoCorrupto() throws IOException {
		levantaArchivo.setFilepath("src/test/resources/EmpresaErronea");
		levantaArchivo.execute();
		assertEquals(0.0, cantidadEmpresasCargadas(), 0.0);
	}
	
	@Test
	public void cargaTodo() { // INCLUIDO DOS ARCHIVOS DE EMPRESAS
		levantaArchivo.setFilepath("src/test/resources/Empresas");
		levantaArchivo.execute();
		assertEquals(3.0, cantidadEmpresasCargadas(), 0.0);
	}
}