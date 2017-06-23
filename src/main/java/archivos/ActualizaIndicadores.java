package archivos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.ObjectMapper;

import domain.indicadores.IndicadorCustom;
import domain.repositorios.RepositorioIndicadores;

public class ActualizaIndicadores {

	private static String filepath;

	public static void setFilepath(String ruta) {
		filepath = ruta;
	}
	
	public String getFilepath() {
		return filepath;
	}

	public void actualizarArchivoJson() throws IOException {
		List<IndicadorCustom> indicadores = RepositorioIndicadores.instance().obtenerCustoms();
		String jsonIndicadores = obtenerJsonCompleto(indicadores);
		borrarArchivoAnterior();
		crearArchivoNuevo(jsonIndicadores);
	}

	private String obtenerJsonCompleto(List<IndicadorCustom> indicadores) throws IOException {
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
