package exceptions;

public class NoHayEmpresasCargadasException extends RuntimeException {
	public NoHayEmpresasCargadasException(){
		super("No hay empresas cargadas en el sistema");
	}
}
