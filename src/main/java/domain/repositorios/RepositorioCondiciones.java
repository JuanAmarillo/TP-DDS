package domain.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import domain.condiciones.Condicion;
import domain.condiciones.condicionesPredeterminadas.CEmpresaMayorAntiguedad;
import domain.condiciones.condicionesPredeterminadas.CEndeudamiento;
import domain.condiciones.condicionesPredeterminadas.CMaximizarROE;
import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;
import exceptions.NoSePuedeBorrarUnPredeterminadoException;
import exceptions.YaExisteLaCondicionException;

public class RepositorioCondiciones  {

	private static List<Condicion> condicionesCargadas;
	private static RepositorioCondiciones instance=null;
	
	public static RepositorioCondiciones instance() {
		if (noHayInstanciaCargada()) 
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		condicionesCargadas= new ArrayList<Condicion>();
		instance = new RepositorioCondiciones();
		agregarPredeterminados();
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
	
	public List<String> getNombresDeCondicionesTaxativas(){
		return getCondicionesCargadas().stream().filter(unaCondicion -> unaCondicion.esTaxativa()).map(unaCondicion -> unaCondicion.getNombre()).collect(Collectors.toList());
	}
	public List<String> getNombresDeCondicionesComparativas(){
		return getCondicionesCargadas().stream().filter(unaCondicion -> unaCondicion.esComparativa()).map(unaCondicion -> unaCondicion.getNombre()).collect(Collectors.toList());
	}
	public List<String> getNombresDeCondiciones(){
		return getCondicionesCargadas().stream().map(unaCondicion -> unaCondicion.getNombre()).collect(Collectors.toList());
	}
	/*
	public Condicion agregarCondicionAPartirDe(String condicion){
		Condicion condicionACargar = new Condicion(condicion);
		condicionExistente(condicionACargar);
		agregarCondicion(condicionACargar);
		return condicionACargar;
	}
	*/
	
	public void agregarCondicion(Condicion condicion) {
		verificarQueNoExista(condicion.getNombre());
		condicionesCargadas.add(condicion);
	}
	
	private void verificarQueNoExista(String nombre){
		if (existeLaCondicion(nombre))
			throw new YaExisteLaCondicionException();
	}

	private boolean existeLaCondicion(String nombre) {
		return condicionesCargadas.stream().anyMatch(condicion -> condicion.suNombreEs(nombre));
	}

	public void eliminarCondicion(String nombre) {
		Condicion condicion = buscarCondicion(nombre).get();
		if(condicion.esCustom)
			condicionesCargadas.remove(condicion);
		else
			throw new NoSePuedeBorrarUnPredeterminadoException();
	}
	
	
	private Optional<Condicion> buscarCondicion(String nombre) {
		return condicionesCargadas.stream().filter(condicion -> condicion.suNombreEs(nombre)).findFirst();
	}

	public static void agregarPredeterminados(){
		condicionesCargadas.add(new TEmpresaMas10Años());
		condicionesCargadas.add(new CEmpresaMayorAntiguedad());
		condicionesCargadas.add(new CEndeudamiento());
		condicionesCargadas.add(new CMaximizarROE());
	}

	public int cantidadDeCondiciones() {
		return getCondicionesCargadas().size();
	}

	
}
