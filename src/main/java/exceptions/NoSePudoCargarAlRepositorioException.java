package exceptions;

@SuppressWarnings("serial")
public class NoSePudoCargarAlRepositorioException extends RuntimeException {
	
	public NoSePudoCargarAlRepositorioException(String entidad) {
		super("No se pudo cargar " + entidad + " correctamente. Intente mas tarde.");
	}

}
