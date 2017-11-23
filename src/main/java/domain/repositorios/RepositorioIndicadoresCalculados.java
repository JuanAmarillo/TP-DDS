package domain.repositorios;

import java.util.NoSuchElementException;
import java.util.Optional;

import domain.Empresa;
import domain.indicadores.Indicador;
import domain.indicadores.IndicadorCalculado;
import exceptions.NoEstaEnLaBDException;

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

	public IndicadorCalculado agregarValores(Indicador indicador, Empresa empresa, String periodo) {
		IndicadorCalculado actualizado = indicador.calcular(empresa, periodo);
		try {
			IndicadorCalculado desactualizado = findBy(indicador, empresa, periodo);		
			desactualizado.setValorExito(actualizado.getValorCalculado().orElse(null));
		} catch (NoEstaEnLaBDException e) {
			agregar(actualizado);
		}
		return actualizado;

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
