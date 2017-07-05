package archivos;

import auxiliaresDeArchivo.DatosIndicadores;
import domain.repositorios.RepositorioIndicadores;

public class LevantaArchivoIndicadores extends FileLoader<DatosIndicadores> {
	
	public LevantaArchivoIndicadores(String filepath) {
		super(filepath,DatosIndicadores.class,RepositorioIndicadores.instance());
	}


}

