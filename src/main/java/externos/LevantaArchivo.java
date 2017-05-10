package externos;

import domain.*;
import domain.repositorios.RepositorioEmpresas;

import java.io.File;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;
import interfaces.*;

public class LevantaArchivo implements FileLoader {
	
	public void cargarArchivo(String filepath) throws IOException {
		Empresa aDevolver = getEmpresaDelArchivo(filepath);
		RepositorioEmpresas.instance().loadEmpresa(aDevolver);
	}

	public Empresa getEmpresaDelArchivo(String filepath) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Empresa aDevolver = mapper.readValue(new File(filepath), Empresa.class);
		return aDevolver;
	}
	
	
}
