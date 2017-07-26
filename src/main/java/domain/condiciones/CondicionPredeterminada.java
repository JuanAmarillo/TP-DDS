package domain.condiciones;

public interface CondicionPredeterminada extends CondicionCustom {
	@Override
	default public Boolean esCustom(){
		return false;
	}
}
