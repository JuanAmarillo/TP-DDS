
package domain.condiciones.OperadoresCondicion;

import javax.persistence.Embeddable;

@Embeddable
public class Menor extends OperadorCondicion{

	public Menor() {
		super("<");
	}

	@Override
	public int comparar(Double valorIndicadorUno, Double valorIndicadorDos) {
		return Double.compare(valorIndicadorDos, valorIndicadorUno);
	}
	
}