package archivos;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import domain.repositorios.Repositorio;


public abstract class FileLoader<T> {
	
	private String filepath;
	private Class<T> typeParameterClass;
	private Repositorio<T> repositorio;
	
	public FileLoader(String filepath,Class<T> typeParameterClass,Repositorio<T> repositorio) {
		this.typeParameterClass = typeParameterClass;
		this.filepath = filepath;
		this.repositorio = repositorio;
	}
	
	public void setFilepath(String filePath){
		this.filepath = filePath;
	}
	
	public T getElementosDelArchivo() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		T aDevolver = mapper.readValue(new File(filepath),typeParameterClass);
		return aDevolver;
	}
	
	public void cargarArchivo() throws IOException {
		T elementos = getElementosDelArchivo();
		repositorio.agregarDesdeArchivo(elementos);
	}
}
