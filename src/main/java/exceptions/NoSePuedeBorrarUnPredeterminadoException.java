package exceptions;

public class NoSePuedeBorrarUnPredeterminadoException extends RuntimeException{
	public NoSePuedeBorrarUnPredeterminadoException(){
		super("No se puede borrar una condicion predeterminada del sistema");
	}
}
