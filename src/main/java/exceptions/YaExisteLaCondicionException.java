package exceptions;

public class YaExisteLaCondicionException extends RuntimeException {

	public String getMessage() {
		return "Ya existe una instancia de esa condicion";
	}
	
}
