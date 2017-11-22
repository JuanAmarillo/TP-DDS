package cacheIndicadores;

import java.util.LinkedHashMap;

import domain.indicadores.IndicadorCalculado;

public class LRU extends AlgortimoDeReemplazo {

	@Override
	public void huboAcceso(LinkedHashMap<String, IndicadorCalculado> enCache, String clave,IndicadorCalculado calculado) {
		enCache.remove(clave);
		enCache.put(clave, calculado);
	}

	@Override
	public void set(LinkedHashMap<String, IndicadorCalculado> enCache, String clave, IndicadorCalculado calculado) {
		if(estaConCapacidadMaxima(enCache))
			enCache.remove(claveMenosUsada(enCache));
		enCache.put(clave, calculado);
	}

	private String claveMenosUsada(LinkedHashMap<String, IndicadorCalculado> enCache) {
		return (String) enCache.keySet().toArray()[0];
	}


}
