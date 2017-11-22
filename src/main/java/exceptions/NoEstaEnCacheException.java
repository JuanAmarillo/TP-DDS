package exceptions;

public class NoEstaEnCacheException extends RuntimeException {
	public NoEstaEnCacheException() {
		super("El elemento buscado no se encuentra en memoria cache");
	}
	
}
