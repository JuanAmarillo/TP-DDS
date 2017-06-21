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
	
	public LevantaArchivoIndicadores(String fp) {
		super(fp,DatosIndicadores.class);
	}
	
	public void cargarArchivo() throws IOException {
		List<IndicadorCustom>indicadoresADevolver = getElementosDelArchivo().getIndicadores();
		RepositorioIndicadores.instance().agregarIndicadores(indicadoresADevolver);
	}


}

