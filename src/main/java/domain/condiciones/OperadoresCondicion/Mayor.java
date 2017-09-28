package domain.condiciones.OperadoresCondicion;

import javax.persistence.Embeddable;

@Embeddable
public class Mayor extends OperadorCondicion {

	public Mayor() {
		super(">");
	}

	@Override
	public int comparar(Double valorIndicadorUno, Double valorIndicadorDos) {
		return Double.compare(valorIndicadorUno, valorIndicadorDos);
	}
	
}