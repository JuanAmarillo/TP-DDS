package cacheIndicadores;

import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;

import domain.Empresa;
import domain.indicadores.Indicador;
import domain.indicadores.IndicadorCalculado;
import exceptions.NoEstaEnCacheException;

public class Cache {
	public static Cache instance = null;
	private Map<String, IndicadorCalculado> indicadoresEnCache = new HashMap<>();
	private AlgortimoDeReemplazo algoritmoDeReemplazo;

	public static Cache instance() {
		if (noHayInstanciaCargada())
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		instance =  new Cache();
		instance.setAlgoritmoDeReemplazo(null);
		instance.setCapacidad(10);
	}
	
	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}
	
	private void setAlgoritmoDeReemplazo(AlgortimoDeReemplazo algoritmoDeReemplazo) {
		this.algoritmoDeReemplazo = algoritmoDeReemplazo;
	}
	
	private void setCapacidad(Integer capacidad) {
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
		return calculado.getNombre()+calculado.getEmpresa().getNombre()+calculado.getPeriodo();
	}
	
	public void set(IndicadorCalculado calculado) {
		algoritmoDeReemplazo.set(indicadoresEnCache,getClave(calculado),calculado);
	}
	
}
