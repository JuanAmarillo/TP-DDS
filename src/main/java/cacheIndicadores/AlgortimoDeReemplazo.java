package cacheIndicadores;

import java.util.Map;

import domain.indicadores.IndicadorCalculado;

public abstract class AlgortimoDeReemplazo {

	protected Integer capacidad;
	
	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public abstract void huboAcceso(Map<String, IndicadorCalculado> indicadoresEnCache, String clave, IndicadorCalculado calculado);

	public abstract void set(Map<String, IndicadorCalculado> indicadoresEnCache, String clave, IndicadorCalculado calculado);
	
}
