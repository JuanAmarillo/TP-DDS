package domain;

public class Indicador {
	public String ecuacion;
	public String nombreIndicador;

	public String getEcuacion() {
		return ecuacion;
	}

	public void setEcuacion(String ecuacion) {
		this.ecuacion = ecuacion;
	}

	public String getNombreIndicador() {
		return nombreIndicador;
	}

	public void setNombreIndicador(String nombreIndicador) {
		this.nombreIndicador = nombreIndicador;
	}

	public boolean suNombreEs(String indicador) {
		return this.nombreIndicador.equals(indicador);
	}

}
