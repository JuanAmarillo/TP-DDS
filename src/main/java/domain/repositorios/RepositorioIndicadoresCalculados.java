package domain.repositorios;

import java.util.List;
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
	public Optional<IndicadorCalculado> findByName(String nombre) {
		return find("nombre + empresa.getNombre() +", nombre);
	}

	@Override
	protected Class<IndicadorCalculado> getEntity() {
		return IndicadorCalculado.class;
	}

}
