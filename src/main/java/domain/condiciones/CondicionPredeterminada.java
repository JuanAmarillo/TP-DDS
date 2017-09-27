package domain.condiciones;

import javax.persistence.Entity;

public interface CondicionPredeterminada extends CondicionCustom {
	@Override
	default public Boolean esCustom() {
		return false;
	}
}
