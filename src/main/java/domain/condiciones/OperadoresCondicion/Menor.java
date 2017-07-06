package domain.condiciones.OperadoresCondicion;

public class Menor implements OperadorCondicion{

	@Override
	public int comparar(Double valorIndicadorUno, Double valorIndicadorDos) {
		return Double.compare(valorIndicadorDos, valorIndicadorUno);
	}
	
}
