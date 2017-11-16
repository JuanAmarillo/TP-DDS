package persistencia;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

import org.codehaus.jackson.map.ObjectMapper;

import domain.*;
import domain.repositorios.RepositorioEmpresas;


public class LevantaArchivoEmpresa{
	
	private String filepath; // Representa la direccion de la carpeta donde estan albergados los archivos
	private final static Logger LOGGER = Logger.getLogger("Carga de archivos");
	
	public LevantaArchivoEmpresa(String filepath) {
		this.filepath = filepath;
	}
	
	public void execute() {
			File[] carpetaDeEmpresas;
			carpetaDeEmpresas = new File(filepath).listFiles();
			for(File archivoDeLaEmpresa : carpetaDeEmpresas) {
				try {
					cargarArchivo(archivoDeLaEmpresa);
					//archivoDeLaEmpresa.delete();
					LOGGER.log(Level.INFO, "Se cargo correctamente el archivo " + archivoDeLaEmpresa.getAbsolutePath()) ;
				}
				catch (IOException e) {
					LOGGER.log(Level.WARNING, e.toString(), e);
				}
			}
	}
	
	public Empresa getEmpresaDelArchivo(File archivoDeLaEmpresa) throws IOException {
		return new ObjectMapper().readValue(archivoDeLaEmpresa,Empresa.class);
	}
	
	public void cargarArchivo(File archivoDeLaEmpresa) throws IOException {
		RepositorioEmpresas.instance().agregar(getEmpresaDelArchivo(archivoDeLaEmpresa));
	}

	public void setFilepath(String filePath){
		this.filepath = filePath;
	}
	
}
