package externos;

import domain.*;
import domain.repositorios.RepositorioEmpresas;

import java.io.File;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;
import interfaces.*;

public class LevantaArchivoEmpresa extends FileLoader<Empresa> {
	
	
	public LevantaArchivoEmpresa(String filepath) {
		super(filepath,Empresa.class);
	}

	@Override
	protected void cargarlosAlRepositorio(Empresa elementos) {
		RepositorioEmpresas.instance().loadEmpresa(elementos);
		
	}
	
	
}
