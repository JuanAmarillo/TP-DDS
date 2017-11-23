package cacheIndicadores;

import domain.Empresa;
import domain.indicadores.Indicador;
import domain.indicadores.IndicadorCalculado;
import domain.repositorios.RepositorioIndicadoresCalculados;
import exceptions.NoEstaEnCacheException;
import exceptions.NoEstaEnLaBDException;
import persistencia.Transaction;

public class ConseguidorDeValores {

	public static IndicadorCalculado get(Indicador indicador, Empresa empresa, String periodo) {
		try {
			return buscarEnCache(indicador, empresa, periodo);
		} catch (NoEstaEnCacheException e) {
			return buscarEnRepoOCalcularlo(indicador, empresa, periodo);
		}
	}

	private static IndicadorCalculado buscarEnRepoOCalcularlo(Indicador indicador, Empresa empresa, String periodo) {
		try {
			return buscarEnRepositorio(indicador,empresa,periodo);
		} catch (NoEstaEnLaBDException e) {
			IndicadorCalculado indicadorCalculado = calcularValor(indicador, empresa, periodo);
			RepositorioIndicadoresCalculados.instance().agregar(indicadorCalculado);
			return indicadorCalculado;
		}
	}

	private static IndicadorCalculado buscarEnRepositorio(Indicador indicador, Empresa empresa, String periodo) {
		IndicadorCalculado calculado = RepositorioIndicadoresCalculados.instance().findBy(indicador,empresa,periodo);
		agregarACache(calculado);
		return calculado;
	}

	private static void agregarACache(IndicadorCalculado calculado) {
		Cache.instance().set(calculado);
	}

	private static IndicadorCalculado calcularValor(Indicador indicador, Empresa empresa, String periodo) {
		return indicador.calcular(empresa, periodo);
	}

	private static IndicadorCalculado buscarEnCache(Indicador indicador, Empresa empresa, String periodo) {
		return Cache.instance().get(indicador, empresa, periodo);
	}

}
