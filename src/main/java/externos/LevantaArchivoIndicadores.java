package externos;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.List;
import domain.indicadores.IndicadorCustom;
import domain.repositorios.RepositorioIndicadores;
import etc.DatosIndicadores;
import interfaces.FileLoader;
import interfaces.Indicador;

public class LevantaArchivoIndicadores implements FileLoader<String> {
	
	@Override
	public void cargarArchivo(String filepath) throws IOException {
		List<IndicadorCustom>indicadoresADevolver = getIndicadoresDelArchivo(filepath).getIndicadores();
		RepositorioIndicadores.instance().agregarIndicadores(indicadoresADevolver);
	}

	public DatosIndicadores getIndicadoresDelArchivo(String filepath) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		DatosIndicadores indicadoresADevolver = mapper.readValue(new File(filepath), DatosIndicadores.class);
		return indicadoresADevolver;
	}


}

