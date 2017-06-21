package interfaces;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import etc.DatosIndicadores;

public abstract class FileLoader<T> {
	
	protected String filepath;
	private Class<T> typeParameterClass;
	
	public FileLoader(String fp,Class<T> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
		this.filepath = fp;
	}
	
	public void setFilepath(String filePath){
		this.filepath = filePath;
	}
	
	public T getElementosDelArchivo() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		T indicadoresADevolver = mapper.readValue(new File(filepath),typeParameterClass);
		return indicadoresADevolver;
	}
	
	
	/* Si se pudiese armar un repo mas genérico, se podría armar
	 * T aDevolver = getElementoDelArchivo(lugarDeCarga);
	 * -->se delega al repositorio correspondiente
	 */
	/*public T getElementoDelArchivo(String lugarDeCarga) throws IOException{
	 * ObjectMapper mapper = new ObjectMapper();
	 * T aDevolver = mapper.readValue(new File(lugarDeCarga), T.class)
	 * return aDevolver;
	 * 
	 */
}
