package externos;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.List;
import domain.indicadores.IndicadorCustom;
import domain.repositorios.RepositorioIndicadores;
import etc.DatosIndicadores;
import interfaces.FileLoader;

public class LevantaArchivoIndicadores implements FileLoader {
	
	private String filepath;
	
	public LevantaArchivoIndicadores(String fp) {
		filepath = fp;
	}
	
	@Override
	public void cargarArchivo() throws IOException {
		List<IndicadorCustom>indicadoresADevolver = getIndicadoresDelArchivo().getIndicadores();
		RepositorioIndicadores.instance().agregarIndicadores(indicadoresADevolver);
	}

	public DatosIndicadores getIndicadoresDelArchivo() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		DatosIndicadores indicadoresADevolver = mapper.readValue(new File(filepath), DatosIndicadores.class);
		return indicadoresADevolver;
	}


}

