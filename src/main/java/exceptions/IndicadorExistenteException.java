package exceptions;

public class IndicadorExistenteException extends RuntimeException {
	public IndicadorExistenteException(){
		super("El indicador a crear ya existe");
	}
}
