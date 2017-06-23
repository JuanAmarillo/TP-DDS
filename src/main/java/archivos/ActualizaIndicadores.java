package archivos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.ObjectMapper;

import domain.indicadores.IndicadorCustom;
import domain.repositorios.RepositorioIndicadores;
import etc.DatosIndicadores;

public class ActualizaIndicadores {

	private static String filepath;

	public static void setFilepath(String ruta) {
		filepath = ruta;
	}
	
	public String getFilepath() {
		return filepath;
	}

	public void actualizarArchivoJson() throws IOException {
		DatosIndicadores indicadores = new DatosIndicadores();
		indicadores.setIndicadores(obtenerIndicadores());
		String jsonIndicadores = obtenerJsonCompleto(indicadores);
		borrarArchivoAnterior();
		crearArchivoNuevo(jsonIndicadores);
	}

	private List<IndicadorCustom> obtenerIndicadores() {
		return RepositorioIndicadores.instance().obtenerCustoms();
	}

	private String obtenerJsonCompleto(DatosIndicadores indicadores) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibilityChecker(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
		String jsonIndicadores = mapper.writeValueAsString(indicadores);
		return jsonIndicadores;
	}

	private void crearArchivoNuevo(String jsonIndicadores) throws IOException {
		FileWriter archivo = new FileWriter(filepath,false);
		archivo.write(jsonIndicadores);
		archivo.close();
	}

	private void  borrarArchivoAnterior() {
		File file = new File(filepath);
		file.delete();
	}
	
}
