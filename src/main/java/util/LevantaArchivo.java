package util;

import domain.*;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public class LevantaArchivo {

	public static Empresa cargarArchivo(String filepath) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(filepath);
		Empresa aDevolver = mapper.readValue(file, Empresa.class);
		return aDevolver;
	}
}
