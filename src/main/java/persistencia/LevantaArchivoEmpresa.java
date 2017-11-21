package persistencia;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.*;

import org.codehaus.jackson.map.ObjectMapper;

import domain.*;
import domain.repositorios.RepositorioEmpresas;

public class LevantaArchivoEmpresa {

	private String sinProcesar; // Representa la direccion de la carpeta donde estan albergados los archivos
	private String procesadosCorrectamente;
	private String procesadosErroneos;
	private final static Logger LOGGER = Logger.getLogger("Carga de archivos");

	public LevantaArchivoEmpresa(String sinProcesar, String procesadosCorrectamente, String procesadosErroneos) {
		this.sinProcesar = sinProcesar;
		this.procesadosCorrectamente = procesadosCorrectamente;
		this.procesadosErroneos = procesadosErroneos;
	}

	public void execute() {
		Arrays.asList(empresasSinProcesar()).stream().forEach(empresa -> cargarArchivo(empresa));
	}

	private File[] empresasSinProcesar() {
		return new File(sinProcesar).listFiles();
	}

	private void cargarArchivo(File empresa) {
		try {
			cargaExitosa(empresa);
		} catch (IOException error) {
			cargaFallida(empresa, error);
		}
	}

	private void cargaFallida(File archivoDeLaEmpresa, IOException error) {
		moverArchivoCargaIncorrecta(archivoDeLaEmpresa);
		LOGGER.log(Level.WARNING, error.toString(), error);
	}

	private void cargaExitosa(File archivoDeLaEmpresa) throws IOException {
		agregarAlRepositorio(archivoDeLaEmpresa);
		moverArchivoCargaCorrecta(archivoDeLaEmpresa);
		LOGGER.log(Level.INFO, "Se cargo correctamente el archivo " + archivoDeLaEmpresa.getAbsolutePath());
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
		return new ObjectMapper().readValue(archivoDeLaEmpresa, Empresa.class);
	}

	public void agregarAlRepositorio(File archivoDeLaEmpresa) throws IOException {
		RepositorioEmpresas.instance().agregar(getEmpresaDelArchivo(archivoDeLaEmpresa));
	}

	public void setFilepath(String filePath) {
		this.sinProcesar = filePath;
	}

}
