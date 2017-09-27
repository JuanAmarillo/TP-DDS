package domain.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import domain.condiciones.Condicion;
import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionTaxativa;
import domain.condiciones.condicionesPredeterminadas.CEmpresaMayorAntiguedad;
import domain.condiciones.condicionesPredeterminadas.CEndeudamiento;
import domain.condiciones.condicionesPredeterminadas.CMaximizarROE;
import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;
import domain.indicadores.IndicadorCustom;
import exceptions.NoSePuedeBorrarUnPredeterminadoException;
import exceptions.YaExisteLaCondicionException;

public class RepositorioCondiciones extends Repositorio<Condicion> {
	private static RepositorioCondiciones instance = null;

	public static RepositorioCondiciones instance() {
		if (noHayInstanciaCargada())
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		instance = new RepositorioCondiciones();
		//instance.agregarPredeterminados();
	}

	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}

	public static void resetSingleton() {
		instance = null;
	}

	public List<CondicionTaxativa> getCondicionesTaxativas() {
		return entityManager.createQuery("SELECT i FROM CondicionTaxativa i", CondicionTaxativa.class).getResultList();
	}

	public List<CondicionComparativa> getCondicionesComparativas() {
		return entityManager.createQuery("SELECT i FROM CondicionComparativa i", CondicionComparativa.class).getResultList();
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

	public void agregarPredeterminados() {
		agregar(new TEmpresaMas10Años());
		agregar(new CEmpresaMayorAntiguedad());
		agregar(new CEndeudamiento());
		agregar(new CMaximizarROE());
	}

	@Override
	protected String getEntityName() {
		return Condicion.class.getSimpleName();
	}

}
