package ui.vm;

import java.io.IOException;

import org.uqbar.commons.utils.Observable;

import persistencia.LevantaArchivoEmpresa;
import persistencia.TransactionManager;

@Observable
public class CargarCuentaVM {

	private String filePath;
	private String nombreCuenta;

	public CargarCuentaVM() {
		super();
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	public void cargarEmpresa() throws IOException {
		if(!filePath.isEmpty()) {
			TransactionManager.instance().crearTransaccion();
			new LevantaArchivoEmpresa(filePath).cargarArchivo();
			TransactionManager.instance().cerrarTransaccion();
		}
		else
			throw new RuntimeException("No hay ningun archivo seleccionado");
	}

	public void resetFilePath() {
		filePath = "";
	}

}
