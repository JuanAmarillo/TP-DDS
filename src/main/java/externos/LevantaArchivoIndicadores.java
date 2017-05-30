package externos;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import domain.Indicador;
import domain.repositorios.RepositorioIndicadores;
import interfaces.FileLoader;

public class LevantaArchivoIndicadores implements FileLoader {

	@Override
	public void cargarArchivo(String filepath) throws IOException {
		Indicador aDevolver = getIndicadorDelArchivo(filepath);
		RepositorioIndicadores.instance().agregarIndicador(aDevolver);
	}

	public Indicador getIndicadorDelArchivo(String filepath) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Indicador aDevolver = mapper.readValue(new File(filepath), Indicador.class);
		return aDevolver;
	}

}
