package externos;

import domain.*;
import domain.repositorios.RepositorioEmpresas;

import java.io.File;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;
import interfaces.*;

public class LevantaArchivoEmpresa extends FileLoader<Empresa> {
	
	
	public LevantaArchivoEmpresa(String fp) {
		super(fp,Empresa.class);
	}
	
	public void cargarArchivo() throws IOException {
		Empresa aDevolver = getElementosDelArchivo();
		RepositorioEmpresas.instance().loadEmpresa(aDevolver);
	}

	public Empresa getEmpresaDelArchivo() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Empresa aDevolver = mapper.readValue(new File(filepath), Empresa.class);
		return aDevolver;
	}
	
	
}
