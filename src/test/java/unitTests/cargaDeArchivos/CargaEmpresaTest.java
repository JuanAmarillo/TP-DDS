package unitTests.cargaDeArchivos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import com.google.common.io.Files;

import domain.Cuenta;
import domain.Empresa;
import domain.repositorios.RepositorioEmpresas;
import persistencia.LevantaArchivoEmpresa;

public class CargaEmpresaTest extends AbstractPersistenceTest {

	public static final String DIRECTORIO_PRUEBAS = "src/test/resources/directorioDePruebas";
	public static final String ARCHIVOS_PRUEBA = "src/test/resources/Empresas";
	
	public RepositorioEmpresas repositorio = new RepositorioEmpresas();
	public LevantaArchivoEmpresa levantaArchivo = new LevantaArchivoEmpresa(DIRECTORIO_PRUEBAS + "/Sin Procesar", DIRECTORIO_PRUEBAS + "/Carga Correcta/", DIRECTORIO_PRUEBAS + "/Carga Incorrecta/");
	public Empresa empresa = new Empresa();
	
	private Long cantidadEmpresasCargadas() {
		return RepositorioEmpresas.instance().cantidadElementosCargados();
	}
	
	@BeforeClass
	public static void creacionDeCarpetas() {
		crearCarpeta(DIRECTORIO_PRUEBAS);
		crearCarpeta(DIRECTORIO_PRUEBAS + "/Carga Correcta");
		crearCarpeta(DIRECTORIO_PRUEBAS + "/Sin Procesar");
		crearCarpeta(DIRECTORIO_PRUEBAS + "/Carga Incorrecta");
	}

	private static void crearCarpeta(String path) {
		File file = new File(path);
		file.mkdir();
	}
	
	@AfterClass
	public static void eliminarCarpetas() throws IOException {
		File file = new File(DIRECTORIO_PRUEBAS);
		FileUtils.deleteDirectory(file);
	}
	
	private int cantidadDeArchivosEn(String destino) {
		File[] file = new File(destino).listFiles();
		int longitud = file.length;
		return longitud;
	}
	
	private void deleteFiles(String carpeta) {
		File[] files = new File(DIRECTORIO_PRUEBAS + carpeta).listFiles();
		for(File file : files) {
			file.delete();
		}
	}
	
	private void traerArchivo(String nombreArchivo) {
		File archivo = new File(ARCHIVOS_PRUEBA + "/" + nombreArchivo);
		File nuevoArchivo = new File(DIRECTORIO_PRUEBAS + "/Sin Procesar/" + nombreArchivo);
		try {
			Files.copy(archivo, nuevoArchivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void traerTodosLosArchivos() {
		File[] archivos = new File(ARCHIVOS_PRUEBA).listFiles();
		for(File archivo : archivos) {
			traerArchivo(archivo.getName());
		}
	}

	@After
	public void despues() {
		deleteFiles("/Carga Correcta/");
		deleteFiles("/Carga Incorrecta/");
		deleteFiles("/Sin Procesar/");
	}
	
	@Test
	public void seCargaElArchivoCorrectamente() {
		traerArchivo("Coca-Cola.json");
		levantaArchivo.execute();
		assertEquals(1.0, cantidadEmpresasCargadas(), 0.0);
	}
	
	@Test
	public void seMueveElArchivoCorrectamente() throws IOException {
		traerArchivo("Coca-Cola.json");
		String destino = DIRECTORIO_PRUEBAS + "/Carga Correcta/";
		levantaArchivo.execute();
		assertEquals(1, cantidadDeArchivosEn(destino));
	}

	@Test
	public void rompeConArchivoCorrupto() throws IOException {
		traerArchivo("LG.json");
		levantaArchivo.execute();
		assertEquals(1, cantidadDeArchivosEn("src/test/resources/directorioDePruebas/Carga Incorrecta/"));
		assertEquals(0.0, cantidadEmpresasCargadas(), 0.0);
	}
	
	@Test
	public void cargaTodo() { // INCLUIDO DOS ARCHIVOS DE EMPRESAS
		traerTodosLosArchivos();
		levantaArchivo.execute();
		assertEquals(2.0, cantidadEmpresasCargadas(), 0.0);
		assertEquals(1, cantidadDeArchivosEn(DIRECTORIO_PRUEBAS + "/Carga Incorrecta/"));
		assertEquals(3, cantidadDeArchivosEn(DIRECTORIO_PRUEBAS + "/Carga Correcta/"));
	}
	
	@Override
	public EntityManager entityManager() {
		return repositorio.getEntityManager();
	}
	
}