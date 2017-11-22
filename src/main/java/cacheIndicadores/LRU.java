package cacheIndicadores;

import java.util.LinkedHashMap;

public class LRU<T> extends AlgortimoDeReemplazo<T> {

	@Override
	public void huboAcceso(LinkedHashMap<String, T> enCache, String clave, T valor) {
		enCache.remove(clave);
		enCache.put(clave, valor);
	}

	@Override
	public void set(LinkedHashMap<String, T> enCache, String clave, T valor) {
		if (estaConCapacidadMaxima(enCache))
			enCache.remove(claveMenosUsada(enCache));
		enCache.put(clave, valor);
	}

	private String claveMenosUsada(LinkedHashMap<String, T> enCache) {
		return (String) enCache.keySet().toArray()[0];
	}

}
