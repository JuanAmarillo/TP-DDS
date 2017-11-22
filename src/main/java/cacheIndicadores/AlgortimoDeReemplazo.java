package cacheIndicadores;

import java.util.LinkedHashMap;
import java.util.Map;

import domain.indicadores.IndicadorCalculado;

public abstract class AlgortimoDeReemplazo {

	protected int capacidad;

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public abstract void huboAcceso(LinkedHashMap<String, IndicadorCalculado> enCache, String clave,
			IndicadorCalculado calculado);

	public abstract void set(LinkedHashMap<String, IndicadorCalculado> enCache, String clave,
			IndicadorCalculado calculado);
	
	protected boolean estaConCapacidadMaxima(LinkedHashMap<String, IndicadorCalculado> enCache) {
		return enCache.size() == capacidad;
	}

}
