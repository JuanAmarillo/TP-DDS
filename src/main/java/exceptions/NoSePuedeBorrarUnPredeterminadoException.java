package exceptions;

public class NoSePuedeBorrarUnPredeterminadoException extends RuntimeException{
	
	public String getMessage() {
		return "No se puede borrar un objeto del sistema";
	}
}
