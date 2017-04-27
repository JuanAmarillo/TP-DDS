package util;
import domain.*;
import domain.repositorios.RepositorioEmpresas;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

public class LevantaArchivo {
	
	private static List<String> archivosCargados = new ArrayList<>();
	private static LevantaArchivo instance = null;
	
	public static Empresa cargarArchivo(String filepath) throws IOException{
		if(!getArchivosCargados().contains(filepath)){
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
	
	public static LevantaArchivo getInstance()
	{
		if(instance==null)
		{
			archivosCargados = new ArrayList<String>();
			instance = new LevantaArchivo();
		}
		return instance;
	}
	
	public static void resetSingleton(){
		instance = null;
	}
	
	private static List<String> getArchivosCargados() {
		return getInstance().archivosCargados;
	}
}
