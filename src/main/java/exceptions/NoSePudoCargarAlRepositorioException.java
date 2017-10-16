package exceptions;

@SuppressWarnings("serial")
public class NoSePudoCargarAlRepositorioException extends RuntimeException {
	
	public NoSePudoCargarAlRepositorioException() {
		super("No se pudo cargar correctamente. Intente mas tarde.");
	}

}
