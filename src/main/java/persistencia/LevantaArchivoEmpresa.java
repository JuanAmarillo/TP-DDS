package persistencia;

import java.io.File;
import static java.nio.file.StandardCopyOption.*;
import java.io.IOException;
import java.util.logging.*;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.common.io.Files;

import domain.*;
import domain.repositorios.RepositorioEmpresas;


public class LevantaArchivoEmpresa{
	
	private String sinProcesar; // Representa la direccion de la carpeta donde estan albergados los archivos
	private String procesadosCorrectamente; 
	private String procesadosErroneos; 
	private final static Logger LOGGER = Logger.getLogger("Carga de archivos");
	
	public LevantaArchivoEmpresa(String sinProcesar, String procesadosCorrectamente, String procesadosErroneos) {
		super();
		this.sinProcesar = sinProcesar;
		this.procesadosCorrectamente = procesadosCorrectamente;
		this.procesadosErroneos = procesadosErroneos;
	}
	
	public void execute() {
		File[] carpetaDeEmpresas;
		carpetaDeEmpresas = new File(sinProcesar).listFiles();
		for(File archivoDeLaEmpresa : carpetaDeEmpresas) {
			try {
				cargarArchivo(archivoDeLaEmpresa);
				moverArchivoCargaCorrecta(archivoDeLaEmpresa);
				LOGGER.log(Level.INFO, "Se cargo correctamente el archivo " + archivoDeLaEmpresa.getAbsolutePath()) ;
			}
			catch (IOException e) {
				moverArchivoCargaIncorrecta(archivoDeLaEmpresa);
				LOGGER.log(Level.WARNING, e.toString(), e);
			}
		}
	}

	public void moverArchivoCargaIncorrecta(File archivoDeLaEmpresa) {
		moveFile(archivoDeLaEmpresa, procesadosErroneos + archivoDeLaEmpresa.getName());
	}

	public void moverArchivoCargaCorrecta(File archivoDeLaEmpresa) {
		moveFile(archivoDeLaEmpresa, procesadosCorrectamente + archivoDeLaEmpresa.getName());
	}
	
	public void moveFile(File archivoDeLaEmpresa, String destino) {
		archivoDeLaEmpresa.renameTo(new File(destino));
	}

	public Empresa getEmpresaDelArchivo(File archivoDeLaEmpresa) throws IOException {
		return new ObjectMapper().readValue(archivoDeLaEmpresa,Empresa.class);
	}
	
	public void cargarArchivo(File archivoDeLaEmpresa) throws IOException {
		RepositorioEmpresas.instance().agregar(getEmpresaDelArchivo(archivoDeLaEmpresa));
	}

	public void setFilepath(String filePath){
		this.sinProcesar = filePath;
	}
	
}
