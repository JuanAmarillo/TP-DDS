package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import domain.repositorios.RepositorioEmpresas;
import externos.LevantaArchivo;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ArchivosTemporalesTest {
	
	public String filename;
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();
	
	@SuppressWarnings("deprecation")
	@Before
	public void escrituraArchivo() throws IOException {
		filename = "/coca-cola-temporal.json";
		final File archivoCocaCola = tempFolder.newFile(filename);
		FileUtils.writeStringToFile(archivoCocaCola, "{ \"nombre\" : \"Coca-Cola\", \"cuentas\": [{\"nombre\" : \"FDS\", \"periodo\": \"2017-06-30 - 2017-12-31\", \"balance\": 98 },"
				+ "{\"nombre\": \"EBITDA\",\"periodo\": \"2do semestre\", \"balance\": 10000}]}");
	}
	
	@After
	public void paraDespues() {
		RepositorioEmpresas.resetSingleton();
	}
	
	@Test
	public void devuelveFilePath() throws IOException{
		final File archivo = tempFolder.newFile("coca-cola.json");
		String s=archivo.getPath();
		System.out.println(s);
	}
	
	@Test
	public void escrituraArchivoFunciona() throws IOException {
		new LevantaArchivo().cargarArchivo(tempFolder.getRoot().toString() + filename);
		assertEquals(1,RepositorioEmpresas.instance().getEmpresasCargadas().size());
	}
	
}
		