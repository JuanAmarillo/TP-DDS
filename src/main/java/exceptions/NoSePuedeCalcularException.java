package exceptions;

public class NoSePuedeCalcularException extends RuntimeException {

	public NoSePuedeCalcularException(String indicador, String empresa, String periodo) {
		super("No se puede calcular el indicador " + indicador + " para la empresa " + empresa + " en el periodo " + periodo);
		// TODO Auto-generated constructor stub
	}

	
}
