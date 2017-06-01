package externos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.common.io.Files;

import java.util.Arrays;
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

	public void agregarIndicadorAlJson(Indicador ind) throws IOException{
		DatosIndicadores inds = new DatosIndicadores();
		inds.getIndicadores().addAll(RepositorioIndicadores.instance().getIndicadoresCargados());
		inds.getIndicadores().add(ind);
		ObjectMapper mapper = new ObjectMapper();
		String jsonIndicadores = mapper.writeValueAsString(inds);
		System.out.println(fp);
		File file = new File(fp);
		file.delete();
		FileWriter f = new FileWriter(fp,false);
		f.write(jsonIndicadores);
		f.close();
	}

}

