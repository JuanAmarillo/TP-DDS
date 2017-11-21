package domain.repositorios;

import java.util.List;

import domain.condiciones.Condicion;
import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionTaxativa;
import domain.condiciones.condicionesPredeterminadas.CEmpresaMayorAntiguedad;
import domain.condiciones.condicionesPredeterminadas.CEndeudamiento;
import domain.condiciones.condicionesPredeterminadas.CMaximizarROE;
import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;
import exceptions.NoSePuedeBorrarUnPredeterminadoException;
import exceptions.YaExisteLaCondicionException;
import persistencia.Transaction;

public class RepositorioCondiciones extends Repositorio<Condicion> {
	private static RepositorioCondiciones instance = null;

	public static RepositorioCondiciones instance() {
		if (noHayInstanciaCargada())
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		instance = new RepositorioCondiciones();
	}

	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}

	public static void resetSingleton() {
		instance = null;
	}

	public List<CondicionTaxativa> getCondicionesTaxativas() {
		return getElementosDe(CondicionTaxativa.class);
	}

	public List<CondicionComparativa> getCondicionesComparativas() {
		return getElementosDe(CondicionComparativa.class);
	}

	@Override
	public void agregar(Condicion condicion) {
		verificarQueNoExista(condicion.getNombre());
		super.agregar(condicion);
	}

	private void verificarQueNoExista(String nombre) {
		if (verificarExistencia(nombre))
			throw new YaExisteLaCondicionException();
	}

	public void eliminarCondicion(String nombre) {
		Condicion condicion = findByName(nombre).get();
		siEsCustomBorrala(condicion);
	}

	public void siEsCustomBorrala(Condicion condicion) {
		if (condicion.esCustom())
			deleteById(condicion.getId());
		else
			throw new NoSePuedeBorrarUnPredeterminadoException();
	}

	@Override
	protected Class<Condicion> getEntity() {
		return Condicion.class;
	}

}
