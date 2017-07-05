package domain.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import auxiliaresDeArchivo.DatosCondiciones;
import domain.condiciones.Condicion;
import domain.condiciones.condicionesPredeterminadas.EmpresaMas10Años;

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
		agregarPredeterminados();
		instance = new RepositorioCondiciones();
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
	
	
	public static void agregarPredeterminados(){
		condicionesCargadas.add(new EmpresaMas10Años());
	}

	@Override
	public void agregarDesdeArchivo(DatosCondiciones elementos) {
		// TODO Auto-generated method stub
		
	}

	
}
