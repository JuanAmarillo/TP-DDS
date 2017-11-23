package cacheIndicadores;

import java.util.LinkedHashMap;

import domain.Empresa;
import domain.indicadores.Indicador;
import domain.indicadores.IndicadorCalculado;
import exceptions.NoEstaEnCacheException;

public class Cache {
	public static Cache instance = null;
	private LinkedHashMap<String, IndicadorCalculado> indicadoresEnCache = new LinkedHashMap<>();
	private AlgortimoDeReemplazo<IndicadorCalculado> algoritmoDeReemplazo;

	public static Cache instance() {
		if (noHayInstanciaCargada())
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		instance =  new Cache();
		instance.setAlgoritmoDeReemplazo(new LRU<IndicadorCalculado>());
	}
	
	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}
	
	private void setAlgoritmoDeReemplazo(AlgortimoDeReemplazo<IndicadorCalculado> algoritmoDeReemplazo) {
		this.algoritmoDeReemplazo = algoritmoDeReemplazo;
	}
	
	public void setCapacidad(Integer capacidad) {
		algoritmoDeReemplazo.setCapacidad(capacidad);
	}
	
	public IndicadorCalculado get(Indicador indicador, Empresa empresa, String periodo) {
		String clave = getClave(indicador, empresa, periodo);
		IndicadorCalculado calculado =  indicadoresEnCache.get(clave);
		verificarExistencia(calculado);
		huboAcceso(clave, calculado);
		return calculado;
	}

	private void huboAcceso(String clave, IndicadorCalculado calculado) {
		algoritmoDeReemplazo.huboAcceso(indicadoresEnCache,clave,calculado);
	}

	private void verificarExistencia(IndicadorCalculado calculado) {
		if(calculado == null)
			throw new NoEstaEnCacheException();
	}

	private String getClave(Indicador indicador, Empresa empresa, String periodo) {
		return indicador.getNombre()+empresa.getNombre()+periodo;
	}
	
	public String getClave(IndicadorCalculado calculado){
		return getClave(calculado.getIndicador(),calculado.getEmpresa(),calculado.getPeriodo());
	}
	
	public void set(IndicadorCalculado calculado) {
		algoritmoDeReemplazo.set(indicadoresEnCache,getClave(calculado),calculado);
	}
	
	public void eliminarEntradaDesactualizada(IndicadorCalculado calculado) {
		indicadoresEnCache.remove(getClave(calculado));
	}
	
}
