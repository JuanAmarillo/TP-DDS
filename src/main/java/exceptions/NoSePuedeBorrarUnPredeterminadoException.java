package exceptions;

public class NoSePuedeBorrarUnPredeterminadoException extends RuntimeException{
	public NoSePuedeBorrarUnPredeterminadoException(){
		super("No se puede borrar un objeto del sistema");
	}
}
