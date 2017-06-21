package archivos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.ObjectMapper;

import domain.repositorios.RepositorioIndicadores;
import etc.DatosIndicadores;

public class ActualizaIndicadores {

	private static String filepath;

	public static void setFilepath(String fp) {
		filepath = fp;
	}
	
	public String getFilepath() {
		return filepath;
	}

	public static void actualizarArchivoJson() throws IOException {
		DatosIndicadores inds = obtenerIndicadores();
		String jsonIndicadores = obtenerJsonCompleto(inds);
		borrarArchivoAnterior();
		crearArchivoNuevo(jsonIndicadores);
	}

	private static String obtenerJsonCompleto(DatosIndicadores indicadores) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibilityChecker(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
		String jsonIndicadores = mapper.writeValueAsString(indicadores);
		return jsonIndicadores;
	}

	private static void crearArchivoNuevo(String jsonIndicadores) throws IOException {
		FileWriter archivo = new FileWriter(filepath,false);
		archivo.write(jsonIndicadores);
		archivo.close();
	}

	private static DatosIndicadores obtenerIndicadores() {
		DatosIndicadores indicadores = new DatosIndicadores();
		indicadores.setIndicadores(RepositorioIndicadores.instance().obtenerCustoms());
		return indicadores;
	}

	private static void  borrarArchivoAnterior() {
		File file = new File(filepath);
		file.delete();
	}
	
}
