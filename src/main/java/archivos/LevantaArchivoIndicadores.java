package archivos;

import domain.repositorios.RepositorioIndicadores;
import etc.DatosIndicadores;

public class LevantaArchivoIndicadores extends FileLoader<DatosIndicadores> {
	
	public LevantaArchivoIndicadores(String filepath) {
		super(filepath,DatosIndicadores.class,RepositorioIndicadores.instance());
	}


}

