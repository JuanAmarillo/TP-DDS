package exceptions;

public class NoHayEmpresasCargadasException extends RuntimeException {

	public String getErrorMessage() {
		return "No hay empresas cargadas en el sistema";
	}
	
}
