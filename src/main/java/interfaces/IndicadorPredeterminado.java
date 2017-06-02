package interfaces;

public abstract class IndicadorPredeterminado implements Indicador{
	
	private String nombreIndicador;
	
	@Override
	public boolean suNombreEs(String nombre) {
		return nombreIndicador.equals(nombre);
	}

	@Override
	public boolean esCustom() {
		return false;
	}

	@Override
	public String getNombre() {
		return nombreIndicador;
	}
}
