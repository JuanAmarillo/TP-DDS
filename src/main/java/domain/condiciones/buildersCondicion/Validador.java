package domain.condiciones.buildersCondicion;

import java.util.ArrayList;
import java.util.List;
import exceptions.*;

public class Validador {
	
	private List<String> errores = new ArrayList<String>();
		
	public Validador agregarValidacion(Boolean condicion, String mensajeError) {
		if(condicion)
			errores.add(mensajeError);
		return this;
	}
	
	public void validar() {
		if(!errores.isEmpty())
			throw new BuilderCondicionesException(generarMensaje());
	}

	private String generarMensaje() {
		String mensajito = String.join("\n", this.errores);
		return mensajito;
	}

}
