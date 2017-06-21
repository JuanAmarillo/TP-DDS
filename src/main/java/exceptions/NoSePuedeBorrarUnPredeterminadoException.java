package exceptions;

public class NoSePuedeBorrarUnPredeterminadoException extends RuntimeException{
	public NoSePuedeBorrarUnPredeterminadoException(){
		super("No se puede borrar un Indicador del sistema");
	}
}
