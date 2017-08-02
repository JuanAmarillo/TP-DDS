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
import exceptions.NoSePuedeBorrarUnPredeterminadoException;
import exceptions.YaExisteLaCondicionException;

public class RepositorioCondiciones {
	private static RepositorioCondiciones instance = null;
	private List<Condicion> condicionesCargadas = new ArrayList<>();

	public static RepositorioCondiciones instance() {
		if (noHayInstanciaCargada())
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		instance = new RepositorioCondiciones();
		instance.agregarPredeterminados();
	}

	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}

	public static void resetSingleton() {
		instance = null;
	}

	public List<Condicion> getCondicionesCargadas() {
		return condicionesCargadas;
	}

	public List<CondicionTaxativa> getCondicionesTaxativas() {
		return getCondicionesCargadas().stream().filter(unaCondicion -> unaCondicion.esTaxativa())
				.map(cond -> (CondicionTaxativa) cond).collect(Collectors.toList());
	}

	public List<CondicionComparativa> getCondicionesComparativas() {
		return getCondicionesCargadas().stream().filter(unaCondicion -> !unaCondicion.esTaxativa())
				.map(unaCondicion -> (CondicionComparativa) unaCondicion).collect(Collectors.toList());
	}

	public List<String> getNombresDeCondiciones() {
		return getCondicionesCargadas().stream().map(unaCondicion -> unaCondicion.getNombre())
				.collect(Collectors.toList());
	}

	public void agregarCondicion(Condicion condicion) {
		verificarQueNoExista(condicion.getNombre());
		add(condicion);
	}

	private void verificarQueNoExista(String nombre) {
		if (existeLaCondicion(nombre))
			throw new YaExisteLaCondicionException();
	}

	public boolean add(Condicion condicion) {
		return condicionesCargadas.add(condicion);
	}

	public void eliminarCondicion(String nombre) {
		Condicion condicion = buscarCondicion(nombre).get();
		siEsCustomBorrala(condicion);
	}

	public void siEsCustomBorrala(Condicion condicion) {
		if (condicion.esCustom())
			remove(condicion);
		else
			throw new NoSePuedeBorrarUnPredeterminadoException();
	}

	public void remove(Condicion condicion) {
		condicionesCargadas.remove(condicion);
	}

	public boolean existeLaCondicion(String nombre) {
		return condicionesCargadas.stream().anyMatch(condicion -> condicion.suNombreEs(nombre));
	}

	public Optional<Condicion> buscarCondicion(String nombre) {
		return condicionesCargadas.stream().filter(condicion -> condicion.suNombreEs(nombre)).findFirst();
	}

	public Integer cantidadDeCondiciones() {
		return getCondicionesCargadas().size();
	}

	public void agregarPredeterminados() {
		condicionesCargadas.add(new TEmpresaMas10Años());
		condicionesCargadas.add(new CEmpresaMayorAntiguedad());
		condicionesCargadas.add(new CEndeudamiento());
		condicionesCargadas.add(new CMaximizarROE());
	}

}
