package interfaces;

public abstract class IndicadorPredeterminado implements Indicador{
	
	private String nombreIndicador;
	
	@Override
	public boolean suNombreEs(String nombre) {
		return getNombreIndicador().equals(nombre);
	}

	@Override
	public boolean esCustom() {
		return false;
	}

	@Override
	public String getNombre() {
		return getNombreIndicador();
	}

	public String getNombreIndicador() {
		return nombreIndicador;
	}

	public void setNombreIndicador(String nombreIndicador) {
		this.nombreIndicador = nombreIndicador;
	}
}
