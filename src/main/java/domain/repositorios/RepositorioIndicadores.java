
package domain.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import auxiliaresDeArchivo.DatosIndicadores;
import domain.indicadores.*;
import domain.indicadores.indicadoresPredeterminados.*;
import exceptions.YaExisteElIndicadorException;
import exceptions.NoSePuedeBorrarUnPredeterminadoException;


public class RepositorioIndicadores implements Repositorio<DatosIndicadores>{
	private static RepositorioIndicadores instance = null;
	private List<Indicador> indicadoresCargados = new ArrayList<>();

	public static RepositorioIndicadores instance() {
		if (noHayInstanciaCargada()) 
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		instance = new RepositorioIndicadores();
		instance.agregarPredeterminados();
	}

	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}
	
	public static void setInstance(RepositorioIndicadores repositorio){
		instance = repositorio;
	}
	
	public  void setIndicadores(List<Indicador> indicadores){
		indicadoresCargados = indicadores;
	}

	public static void resetSingleton() {
		instance = null;
	}

	public List<Indicador> getIndicadoresCargados() {
		return indicadoresCargados;
	}


	public void eliminarIndicadorAPartirDel(String nombreIndicador) {
		Indicador indicadorASacar = buscarIndicador(nombreIndicador).get();
		eliminarIndicador(indicadorASacar);
	}

	public void eliminarIndicador(Indicador indicador) throws NoSePuedeBorrarUnPredeterminadoException{
		if(indicador.esCustom())
			remove(indicador);
		else 
			throw new NoSePuedeBorrarUnPredeterminadoException();
		
	}

	public void remove(Indicador indicador) {
		getIndicadoresCargados().remove(indicador);
	}

	public List<IndicadorCustom> obtenerCustoms() {
		return indicadoresCargados.stream().filter(ind -> ind.esCustom()).map(ind -> (IndicadorCustom) ind)
				.collect(Collectors.toList());
	}

	public void agregarIndicador(Indicador indicador) {
		verificarSiExiste(indicador);
		add(indicador);
	}

	public void add(Indicador indicador) {
		getIndicadoresCargados().add(indicador);
	}

	public void agregarDesdeArchivo(DatosIndicadores datosIndicadores) {
		List<IndicadorCustom> indicadores = datosIndicadores.buildIndicadores();
		indicadoresCargados.addAll(indicadores);
	}

	private void verificarSiExiste(Indicador indicador) {
		if (contieneElIndicador(indicador.getNombre()))
			throw new YaExisteElIndicadorException();
	}

	public Optional<Indicador> buscarIndicador(String nombre) {
		return getIndicadoresCargados().stream().filter(unIndicador -> unIndicador.suNombreEs(nombre)).findFirst();
	}

	public boolean contieneElIndicador(String nombre) {
		return getIndicadoresCargados().stream().anyMatch(unIndicador -> unIndicador.suNombreEs(nombre));
	}

	public List<String> getNombresDeIndicadores() {
		return getIndicadoresCargados().stream().map(unIndicador -> unIndicador.getNombre()).collect(Collectors.toList());
	}

	public  void agregarPredeterminados() {
		indicadoresCargados.add(new Leverage());
		indicadoresCargados.add(new ROA());
		indicadoresCargados.add(new ROE());
		indicadoresCargados.add(new Antiguedad());
		indicadoresCargados.add(new Solvencia());
		indicadoresCargados.add(new Endeudamiento());
		indicadoresCargados.add(new RAC());
	}
	
}