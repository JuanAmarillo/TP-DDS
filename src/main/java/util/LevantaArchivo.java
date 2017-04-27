package util;

import domain.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

public class LevantaArchivo {
	private static  List<String> archivosCargados = new ArrayList<>();
	
	public static Empresa cargarArchivo(String filepath) throws IOException {
		if(!getArchivosCargados().contains(filepath)) {
			getArchivosCargados().add(filepath);
			ObjectMapper mapper = new ObjectMapper();
			File file = new File(filepath);
			Empresa aDevolver = mapper.readValue(file, Empresa.class);
			return aDevolver;
		}
		else
		{
			throw new AlreadyUploadedException();
		}
	}
	
	public static  List<String> getArchivosCargados() {
		return archivosCargados;
	}
	
	public static void resetFiles() {
		archivosCargados = new ArrayList<>();
	}
}
