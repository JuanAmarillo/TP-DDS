package domain.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import auxiliaresDeArchivo.DatosCondiciones;
import domain.condiciones.Condicion;
import domain.condiciones.condicionesPredeterminadas.CEmpresaMayorAntiguedad;
import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;
import exceptions.YaExisteLaCondicionException;

public class RepositorioCondiciones implements Repositorio<DatosCondiciones> {

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
	
	public List<String> getNombresDeCondiciones(){
		return getCondicionesCargadas().stream().map(unaCond -> unaCond.getNombre()).collect(Collectors.toList());
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
		if (condicionesCargadas.stream().anyMatch(condicion -> condicion.suNombreEs(nombre)))
		{
			throw new YaExisteLaCondicionException();
		}
	}

	public static void agregarPredeterminados(){
		condicionesCargadas.add(new TEmpresaMas10Años());
		condicionesCargadas.add(new CEmpresaMayorAntiguedad());
	}

	@Override
	public void agregarDesdeArchivo(DatosCondiciones elementos) {
		// TODO Auto-generated method stub
		
	}

	public int cantidadDeCondiciones() {
		return getCondicionesCargadas().size();
	}

	
}
