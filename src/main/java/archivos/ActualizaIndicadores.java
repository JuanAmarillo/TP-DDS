package archivos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.ObjectMapper;

import auxiliaresDeArchivo.DatosIndicadores;
import domain.indicadores.BuilderIndicadorCustom;
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
	
	public void agregar(String indicador) throws IOException{
		RepositorioIndicadores.instance().agregarIndicador(crearIndicador(indicador));
		actualizarArchivoJson();
	}
	
	public IndicadorCustom crearIndicador(String ecuacion) {
		return new BuilderIndicadorCustom(ecuacion).analizar().setEcuacion().setCalculo().build();
	}
	
	public void eliminar(String nombreIndicador) throws IOException{
		RepositorioIndicadores.instance().eliminarIndicadorAPartirDel(nombreIndicador);
		actualizarArchivoJson();
	}

	private void actualizarArchivoJson() throws IOException {
		DatosIndicadores indicadores = new DatosIndicadores();
		indicadores.setIndicadores(obtenerIndicadores());
		String jsonIndicadores = obtenerJsonCompleto(indicadores);
		actualizarArchivo(jsonIndicadores);
	}

	private void actualizarArchivo(String jsonIndicadores) throws IOException {
		borrarArchivoAnterior();
		crearArchivoNuevo(jsonIndicadores);
	}

	private List<BuilderIndicadorCustom> obtenerIndicadores() {
		return generarBuilders(RepositorioIndicadores.instance().obtenerCustoms());
	}
	
	private List<BuilderIndicadorCustom> generarBuilders(List<IndicadorCustom> indicadores) {
		return indicadores.stream().map(ind -> new BuilderIndicadorCustom(ind.getNombre(), ind.getExpresion())).collect(Collectors.toList());
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
		new File(filepath).delete();
	}
	
}
