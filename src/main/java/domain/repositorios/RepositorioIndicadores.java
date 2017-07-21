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

	public void agregarIndicadorAPartirDe(String ecuacion) {
		IndicadorCustom indicadorACargar = new BuilderIndicadorCustom(ecuacion).analizar().setEcuacion().setCalculo().build();
		existeElIndicador(indicadorACargar);
		agregarIndicador(indicadorACargar);
	}

	public void eliminarIndicadorAPartirDe(String nombreIndicador) {
		Indicador indicadorASacar = buscarIndicador(nombreIndicador).get();
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

	public void agregarDesdeArchivo(DatosIndicadores datosIndicadores) {
		List<IndicadorCustom> indicadores = datosIndicadores.getIndicadores();
		generarCalculoIndicadores(indicadores);
		indicadoresCargados.addAll(indicadores);
	}
	
	private void generarCalculoIndicadores(List<IndicadorCustom> indicadores){
		indicadores.stream().forEach(indicador -> indicador.setCalculo());
	}

	private void existeElIndicador(IndicadorCustom indicador) {
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

	public static void agregarPredeterminados() {
		indicadoresCargados.add(new Leverage());
		indicadoresCargados.add(new ROA());
		indicadoresCargados.add(new ROE());
		indicadoresCargados.add(new Antiguedad());
		indicadoresCargados.add(new Solvencia());
		indicadoresCargados.add(new Endeudamiento());
		indicadoresCargados.add(new RAC());
	}
	
}
