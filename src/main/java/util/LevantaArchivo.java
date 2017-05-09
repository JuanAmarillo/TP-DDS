package util;

import domain.*;

import java.io.File;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;

import domain.repositorios.RepositorioEmpresas;

public class LevantaArchivo {
	
	public Empresa cargarArchivo(String filepath) throws IOException {
		Empresa aDevolver = getEmpresaDelArchivo(filepath);
		RepositorioEmpresas.instance().loadEmpresa(aDevolver);
		return aDevolver;
	}

	public Empresa getEmpresaDelArchivo(String filepath) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Empresa aDevolver = mapper.readValue(new File(filepath), Empresa.class);
		return aDevolver;
	}
	
	
}
