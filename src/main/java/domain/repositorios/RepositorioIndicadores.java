package domain.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import domain.indicadores.*;
import domain.indicadores.indicadoresPredeterminados.*;
import exceptions.IndicadorExistenteException;
import exceptions.NoSePuedeBorrarUnPredeterminadoException;


public class RepositorioIndicadores {
	private static List<Indicador> indicadoresCargados;
	private static RepositorioIndicadores instance = null;

	public static RepositorioIndicadores instance() {
		if (noHayInstanciaCargada()) 
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		indicadoresCargados = new ArrayList<Indicador>();
		agregarPredeterminados();
		instance = new RepositorioIndicadores();
	}

	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}

	public static void resetSingleton() {
		instance = null;
	}

	public List<Indicador> getIndicadoresCargados() {
		return indicadoresCargados;
	}

	public IndicadorCustom agregarIndicadorAPartirDe(String indicador) {
		IndicadorCustom indicadorACargar = new IndicadorCustom(indicador);
		indicadorExistente(indicadorACargar);
		agregarIndicador(indicadorACargar);
		return indicadorACargar;
	}

	public void eliminarIndicadorAPartirDe(String nombre) {
		Indicador indicadorASacar = buscarIndicador(nombre).get();
		eliminarIndicador(indicadorASacar);
	}

	public void eliminarIndicador(Indicador indicador) throws NoSePuedeBorrarUnPredeterminadoException{
		if(indicador.esCustom()) 
			getIndicadoresCargados().remove(indicador);
		else 
			throw new NoSePuedeBorrarUnPredeterminadoException();
		
	}

	public List<IndicadorCustom> obtenerCustoms() {
		return indicadoresCargados.stream().filter(ind -> ind.esCustom()).map(ind -> (IndicadorCustom) ind)
				.collect(Collectors.toList());
	}

	public void agregarIndicador(IndicadorCustom indicador) {
		getIndicadoresCargados().add(indicador);
	}

	public void agregarIndicadores(List<IndicadorCustom> indicadores) {
		generarCalculoIndicadores(indicadores);
		indicadoresCargados.addAll(indicadores);
	}
	
	private void generarCalculoIndicadores(List<IndicadorCustom> indicadores){
		indicadores.stream().forEach(indicador -> indicador.setCalculo());
	}

	private void indicadorExistente(IndicadorCustom indicador) {
		if (contieneElIndicador(indicador.getNombre()))
			throw new IndicadorExistenteException();
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

	public static void agregarPredeterminados() {
		indicadoresCargados.add(new Leverage());
		indicadoresCargados.add(new ROA());
		indicadoresCargados.add(new ROE());
	}
	
}
