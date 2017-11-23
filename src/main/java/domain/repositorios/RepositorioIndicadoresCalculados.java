package domain.repositorios;

import java.util.NoSuchElementException;
import java.util.Optional;

import cacheIndicadores.Cache;
import domain.Empresa;
import domain.indicadores.Indicador;
import domain.indicadores.IndicadorCalculado;
import exceptions.NoEstaEnLaBDException;
import persistencia.Transaction;

public class RepositorioIndicadoresCalculados extends Repositorio<IndicadorCalculado> {

	private static RepositorioIndicadoresCalculados instance = null;

	public static RepositorioIndicadoresCalculados instance() {
		if (noHayInstanciaCargada())
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		instance = new RepositorioIndicadoresCalculados();
	}

	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}

	@Override
	public Optional<IndicadorCalculado> findByName(String nombre) {
		return find("nombre + empresa.getNombre() +", nombre);
	}

	@Override
	protected Class<IndicadorCalculado> getEntity() {
		return IndicadorCalculado.class;
	}

	public void agregarValores(Indicador indicador, Empresa empresa, String periodo) {
		IndicadorCalculado actualizado = indicador.calcular(empresa, periodo);
		System.out.println("nombre " + actualizado.getNombre() + " valor " + actualizado.getValorString());
		try {
			IndicadorCalculado desactualizado = findBy(indicador, empresa, periodo);		
			actualizarValor(actualizado, desactualizado);
			eliminarDeCache(desactualizado);
		} catch (NoEstaEnLaBDException e) {
			agregar(actualizado);
		}
	}

	private void actualizarValor(IndicadorCalculado actualizado, IndicadorCalculado desactualizado) {
		deleteById(desactualizado.getId());
		agregar(actualizado);
	}

	private void eliminarDeCache(IndicadorCalculado desactualizado) {
		Cache.instance().eliminarEntradaDesactualizada(desactualizado);
	}

	public IndicadorCalculado findBy(Indicador indicador, Empresa empresa, String periodo) {
		return obtenerLista(query(indicador,empresa,periodo), IndicadorCalculado.class).stream().findFirst()
				.orElseThrow(NoEstaEnLaBDException::new);
	}

	private String query(Indicador indicador, Empresa empresa, String periodo) {
		return from() + where("periodo", periodo) + and("empresa_id", empresa.getId())
				+ and("indicador_id", indicador.getId());
	}
}
