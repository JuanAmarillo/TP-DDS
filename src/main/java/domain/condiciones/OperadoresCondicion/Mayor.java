package domain.condiciones.OperadoresCondicion;

public class Mayor implements OperadorCondicion {

	@Override
	public int comparar(Double valorIndicadorUno, Double valorIndicadorDos) {
		return Double.compare(valorIndicadorUno, valorIndicadorDos);
	}
	
}
