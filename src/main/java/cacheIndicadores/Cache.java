package cacheIndicadores;

import java.util.HashMap;
import java.util.Map;

import domain.Empresa;
import domain.indicadores.Indicador;
import domain.indicadores.IndicadorCalculado;

public class Cache {
	public static Cache instance = null;
	private Map<String, IndicadorCalculado> indicadoresEnCache = new HashMap<>();
	private AlgortimoDeReemplazo reemplazo;

	public static Cache instance() {
		if (noHayInstanciaCargada())
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		instance =  new Cache();
	}
	
	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}
	
	public IndicadorCalculado get(Indicador indicador, Empresa empresa, String periodo) {
		return indicadoresEnCache.get(getClave(indicador, empresa, periodo));
	}

	private String getClave(Indicador indicador, Empresa empresa, String periodo) {
		return indicador.getNombre()+empresa.getNombre()+periodo;
	}
	
	public void set(IndicadorCalculado calculado) {
		
	}
}
