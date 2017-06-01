package externos;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import domain.Indicador;
import domain.repositorios.RepositorioIndicadores;
import interfaces.FileLoader;

public class LevantaArchivoIndicadores implements FileLoader<String> {
	

	@Override
	public void cargarArchivo(String filepath) throws IOException {
		List<Indicador>indicadoresADevolver = getIndicadoresDelArchivo(filepath);
		RepositorioIndicadores.instance().agregarIndicadores(indicadoresADevolver);
	}

	public List<Indicador> getIndicadoresDelArchivo(String filepath) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Indicador> indicadoresADevolver = Arrays.asList(mapper.readValue(new File(filepath), Indicador[].class));
		return indicadoresADevolver;
	}

}

