
package domain.repositorios;

import java.util.List;
import java.util.stream.Collectors;

import domain.indicadores.BuilderIndicadorCustom;
import domain.indicadores.Indicador;
import domain.indicadores.IndicadorCustom;
import domain.indicadores.indicadoresPredeterminados.Antiguedad;
import domain.indicadores.indicadoresPredeterminados.Endeudamiento;
import domain.indicadores.indicadoresPredeterminados.Leverage;
import domain.indicadores.indicadoresPredeterminados.RAC;
import domain.indicadores.indicadoresPredeterminados.ROA;
import domain.indicadores.indicadoresPredeterminados.ROE;
import domain.indicadores.indicadoresPredeterminados.Solvencia;
import exceptions.NoSePuedeBorrarUnPredeterminadoException;
import exceptions.YaExisteElIndicadorException;
import persistencia.Transaction;

public class RepositorioIndicadores extends Repositorio<Indicador> {
	private static RepositorioIndicadores instance = null;

	public static RepositorioIndicadores instance() {
		if (noHayInstanciaCargada())
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		instance = new RepositorioIndicadores();
	}

	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}

	public static void setInstance(RepositorioIndicadores repositorio) {
		instance = repositorio;
	}

	@Override
	protected Class<Indicador> getEntity() {
		return Indicador.class;
	}

	public static void resetSingleton() {
		instance = null;
	}

	@Override
	public void deleteByName(String nombre) {
		Indicador indicadorASacar = findByName(nombre).get();
		deleteById(indicadorASacar);
	}

	public void deleteById(Indicador indicador) throws NoSePuedeBorrarUnPredeterminadoException {
		if (indicador.esCustom())
			deleteById(indicador.getId());
		else
			throw new NoSePuedeBorrarUnPredeterminadoException();
	}

	public List<IndicadorCustom> obtenerCustoms() {
		return getElementosDe(IndicadorCustom.class);
	}

	public void agregarIndicadorAPartirDel(String nombreIndicador) {
		IndicadorCustom indicadorNuevo = crearIndicador(nombreIndicador);
		agregar(indicadorNuevo);
	}

	@Override
	public void agregar(Indicador indicadorNuevo) {
		verificarSiExiste(indicadorNuevo);
		super.agregar(indicadorNuevo);
	}

	private void verificarSiExiste(Indicador indicador) {
		if (verificarExistencia(indicador.getNombre()))
			throw new YaExisteElIndicadorException();
	}

	public IndicadorCustom crearIndicador(String ecuacion) {
		return new BuilderIndicadorCustom(ecuacion).analizar().setEcuacion().setCalculo().build();
	}

}