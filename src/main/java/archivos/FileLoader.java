package archivos;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;


public abstract class FileLoader<T> {
	
	protected String filepath;
	private Class<T> typeParameterClass;
	
	public FileLoader(String filepath,Class<T> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
		this.filepath = filepath;
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
		cargarlosAlRepositorio(elementos);
	}
	
	protected abstract void cargarlosAlRepositorio(T elementos);
}
