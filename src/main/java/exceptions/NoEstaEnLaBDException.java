package exceptions;

public class NoEstaEnLaBDException extends RuntimeException {
	public NoEstaEnLaBDException() {
		super("No esta cargado en la base de datos");
	}
}
