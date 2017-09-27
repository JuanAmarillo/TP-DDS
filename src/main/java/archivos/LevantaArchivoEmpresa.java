package archivos;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import domain.*;
import domain.repositorios.RepositorioEmpresas;


public class LevantaArchivoEmpresa{
	
	private String filepath;
	
	public LevantaArchivoEmpresa(String filepath) {
		this.filepath = filepath;
	}
	
	public void setFilepath(String filePath){
		this.filepath = filePath;
	}
	
	public Empresa getEmpresaDelArchivo() throws IOException {
		return new ObjectMapper().readValue(new File(filepath),Empresa.class);
	}
	
	public void cargarArchivo() throws IOException {
		RepositorioEmpresas.instance().agregar(getEmpresaDelArchivo());
	}
	
}
