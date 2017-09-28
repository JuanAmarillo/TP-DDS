package exceptions;

@SuppressWarnings("serial")
public class NoSePudoCargarAlRepositorioException extends RuntimeException {
	
	public NoSePudoCargarAlRepositorioException(String mensajeDeError) {
		super(mensajeDeError);
	}

}
