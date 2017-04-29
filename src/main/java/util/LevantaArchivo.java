package util;

import domain.*;

import java.io.File;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;

import domain.repositorios.RepositorioEmpresas;
import exceptions.NoExisteLaEmpresaException;

public class LevantaArchivo {
	
	public Empresa cargarArchivo(String filepath) throws IOException {
		Empresa aDevolver = getEmpresaDelArchivo(filepath);
		loadEmpresa(aDevolver);
		return aDevolver;
	}

	public Empresa getEmpresaDelArchivo(String filepath) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Empresa aDevolver = mapper.readValue(new File(filepath), Empresa.class);
		return aDevolver;
	}
	
	public void loadEmpresa(Empresa empresaLeida) {
		try {
			Empresa empresa = RepositorioEmpresas.getInstance().getEmpresaCargada(empresaLeida);
			
		}
		catch(NoExisteLaEmpresaException e) {
			RepositorioEmpresas.getInstance().agregarEmpresa(empresaLeida);
		}
	}
}
