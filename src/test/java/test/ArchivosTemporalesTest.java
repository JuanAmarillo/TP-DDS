package test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ArchivosTemporalesTest {
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();
	
	@SuppressWarnings("deprecation")
	@Test
	public void testWrite() throws IOException{
		final File archivo = tempFolder.newFile("coca.json");
		FileUtils.writeStringToFile(archivo, "cocaCola");
		final String s = FileUtils.readFileToString(archivo);
		Assert.assertEquals("cocaCola", s);
	}
}
		