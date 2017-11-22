package domain.repositorios;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import domain.Empresa;
import domain.indicadores.Indicador;
import domain.indicadores.IndicadorCalculado;

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
	protected Class<IndicadorCalculado> getEntity() {
		return IndicadorCalculado.class;
	}

	public void agregarValores(IndicadorCalculado indicadorCalculado) {
		Optional<IndicadorCalculado> indicador = obtenerIndicador(indicadorCalculado);
		Double valorCalculado = indicadorCalculado.getValorCalculado().orElse(null);
		try {
			indicador.get().setValorExito(valorCalculado);
		}
		catch(NoSuchElementException e) {
			agregar(indicadorCalculado);
		}
	}

	private Optional<IndicadorCalculado> obtenerIndicador(IndicadorCalculado calcular) {
		return obtenerLista(query(calcular), IndicadorCalculado.class).stream().findFirst();
	}

	private String query(IndicadorCalculado calcular) {
		String query = from() ;
		query += where("periodo", calcular.getPeriodo());
		query += and("empresa_id", calcular.getEmpresa().getId());
		query += and("indicador_id", calcular.getIndicador().getId());
		return query;
	}

}
