package interfaces;

import java.io.IOException;

public interface FileLoader<T> {
	public void cargarArchivo(String lugarDeCarga) throws IOException;
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
