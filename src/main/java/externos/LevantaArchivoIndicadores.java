package externos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.List;
import domain.Indicador;
import domain.repositorios.RepositorioIndicadores;
import etc.DatosIndicadores;
import interfaces.FileLoader;

public class LevantaArchivoIndicadores implements FileLoader<String> {
	public String fp = "src/test/resources/Indicadores.json";

	@Override
	public void cargarArchivo(String algo) throws IOException {
		List<Indicador>indicadoresADevolver = getIndicadoresDelArchivo(fp).getIndicadores();
		RepositorioIndicadores.instance().agregarIndicadores(indicadoresADevolver);
	}

	public DatosIndicadores getIndicadoresDelArchivo(String filepath) throws IOException {
		fp = filepath;
		ObjectMapper mapper = new ObjectMapper();
		DatosIndicadores indicadoresADevolver = mapper.readValue(new File(filepath), DatosIndicadores.class);
		return indicadoresADevolver;
	}

	public void actualizarArchivoJson() throws IOException{
		DatosIndicadores inds = obtenerIndicadores();
		String jsonIndicadores = obtenerJsonCompleto(inds);
		borrarArchivoAnterior();
		crearArchivoNuevo(jsonIndicadores);
	}

	private String obtenerJsonCompleto(DatosIndicadores inds)
			throws IOException, JsonGenerationException, JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibilityChecker(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
		String jsonIndicadores = mapper.writeValueAsString(inds);
		return jsonIndicadores;
	}

	private void crearArchivoNuevo(String jsonIndicadores) throws IOException {
		FileWriter f = new FileWriter(fp,false);
		f.write(jsonIndicadores);
		f.close();
	}

	private DatosIndicadores obtenerIndicadores() {
		DatosIndicadores inds = new DatosIndicadores();
		inds.getIndicadores().addAll(RepositorioIndicadores.instance().getIndicadoresCargados());
		return inds;
	}

	private void borrarArchivoAnterior() {
		File file = new File(fp);
		file.delete();
	}

}

