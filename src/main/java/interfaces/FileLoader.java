package interfaces;

import java.io.IOException;

public interface FileLoader<T> {
	public void cargarArchivo(T lugarDeCarga) throws IOException;
}
