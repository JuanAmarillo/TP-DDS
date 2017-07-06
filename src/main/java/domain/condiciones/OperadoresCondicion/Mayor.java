package domain.condiciones.OperadoresCondicion;

public class Mayor extends OperadorCondicion {

	public Mayor() {
		super(">");
	}

	@Override
	public int comparar(Double valorIndicadorUno, Double valorIndicadorDos) {
		return Double.compare(valorIndicadorUno, valorIndicadorDos);
	}
	
}
