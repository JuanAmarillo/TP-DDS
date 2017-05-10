package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ArchivosTemporalesTest {
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();
	
	@SuppressWarnings("deprecation")
	@Before
	public void escrituraArchivo() throws IOException {
		final File archivoCocaCola = tempFolder.newFile("coca-cola-temporal.json");
		FileUtils.writeStringToFile(archivoCocaCola, "{"
				+ " nombre"
				+ ":"
				+ "Coca-Cola,"
				+ ""
				+ "cuentas: ["
				+ "{"
				+ "nombre=FDS,"
				+ "periodo: 2017-06-30 - 2017-12-31,"
				+ "balance: 98"
				+ "},"
				+ "{"
				+ "nombre: EBITDA,"
				+ "periodo: 2do semestre,"
				+ "balance: 10000"
				+ "}"
				+ "]"
				+ "}");
	}
	
	@Test
	public void devuelveFilePath() throws IOException{
		final File archivo = tempFolder.newFile("coca-cola.json");
		String s=archivo.getPath();
		System.out.println(s);
		
	}
}
		