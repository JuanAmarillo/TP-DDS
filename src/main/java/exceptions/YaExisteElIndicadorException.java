package exceptions;

public class YaExisteElIndicadorException extends RuntimeException {
	public YaExisteElIndicadorException(){
		super("El indicador a crear ya existe");
	}
}
