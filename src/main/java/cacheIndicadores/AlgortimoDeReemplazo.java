package cacheIndicadores;

import java.util.LinkedHashMap;

public abstract class AlgortimoDeReemplazo<T> {

	protected int capacidad = 10;

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public abstract void huboAcceso(LinkedHashMap<String, T> enCache, String clave, T calculado);

	public abstract void set(LinkedHashMap<String, T> enCache, String clave, T calculado);

	protected boolean estaConCapacidadMaxima(LinkedHashMap<String, T> enCache) {
		return enCache.size() == capacidad;
	}

}
