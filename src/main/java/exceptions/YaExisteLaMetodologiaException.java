package exceptions;

public class YaExisteLaMetodologiaException extends RuntimeException{
	public YaExisteLaMetodologiaException(){
		super("La metodologia a crear ya existe");
	}

}
