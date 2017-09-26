
package domain.condiciones.OperadoresCondicion;

public class Menor extends OperadorCondicion{

	public Menor() {
		super("<");
	}

	@Override
	public int comparar(Double valorIndicadorUno, Double valorIndicadorDos) {
		return Double.compare(valorIndicadorDos, valorIndicadorUno);
	}
	
}