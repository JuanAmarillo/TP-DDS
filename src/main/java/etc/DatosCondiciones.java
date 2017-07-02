package etc;

import java.util.ArrayList;
import java.util.List;

import domain.condiciones.*;

public class DatosCondiciones {
	List<Condicion> condiciones;

	public DatosCondiciones() {
		condiciones = new ArrayList<Condicion>();
	}

	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}
}
