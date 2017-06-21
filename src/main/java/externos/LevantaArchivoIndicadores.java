package externos;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.List;
import domain.indicadores.IndicadorCustom;
import domain.repositorios.RepositorioIndicadores;
import etc.DatosIndicadores;
import interfaces.FileLoader;

public class LevantaArchivoIndicadores extends FileLoader<DatosIndicadores> {
	
	public LevantaArchivoIndicadores(String filepath) {
		super(filepath,DatosIndicadores.class);
	}

	@Override
	protected void cargarlosAlRepositorio(DatosIndicadores elementos) {
		RepositorioIndicadores.instance().agregarIndicadores(elementos.getIndicadores());
	}


}

