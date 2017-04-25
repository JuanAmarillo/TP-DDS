package ui.vm;
import org.uqbar.commons.utils.Observable;

@Observable
public class CargarCuentaVM {
	
	private String filePath;
	private String nombreCuenta;
	
	public CargarCuentaVM(){
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

}
