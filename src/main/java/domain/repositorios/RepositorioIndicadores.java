
package domain.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

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


public class RepositorioIndicadores extends Repositorio<Indicador>{
	private EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	private static RepositorioIndicadores instance = null;
	private List<Indicador> indicadoresCargados = new ArrayList<>();

	public static RepositorioIndicadores instance() {
		if (noHayInstanciaCargada()) 
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		instance = new RepositorioIndicadores();
		instance.leerBD();
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
			remove((IndicadorCustom) indicador);
		else 
			throw new NoSePuedeBorrarUnPredeterminadoException();		
	}

	public void remove(IndicadorCustom indicador) {
		getIndicadoresCargados().remove(indicador);
		eliminarDeLaBD(indicador);
	}

	private void eliminarDeLaBD(IndicadorCustom indicador) {
		IndicadorCustom aEliminar = entityManager.find(IndicadorCustom.class, indicador.getId());
		entityManager.getTransaction().begin();
		entityManager.remove(aEliminar);
		entityManager.getTransaction().commit();		
	}

	public List<IndicadorCustom> obtenerCustoms() {
		return indicadoresCargados.stream().filter(ind -> ind.esCustom()).map(ind -> (IndicadorCustom) ind)
				.collect(Collectors.toList());
	}
	
	public void agregarIndicadorAPartirDel(String nombreIndicador) {
		IndicadorCustom indicadorNuevo = crearIndicador(nombreIndicador);
		agregarIndicador(indicadorNuevo);		
	}
	
	public void agregarIndicador(IndicadorCustom indicadorNuevo) {
		verificarSiExiste(indicadorNuevo);
		persistirIndicador(indicadorNuevo);
		add(indicadorNuevo);
	}
	
	public void persistirIndicador(IndicadorCustom indicador) {		
		entityManager.getTransaction().begin();
		entityManager.persist(indicador);
		entityManager.getTransaction().commit();		
	}
	
	public void add(Indicador indicador) {
		getIndicadoresCargados().add(indicador);
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
	
	public void leerBD() {
		BuilderIndicadorCustom buildercito = new BuilderIndicadorCustom(null);
		List<IndicadorCustom> indicadoresEnBase = entityManager.createQuery("SELECT i FROM IndicadorCustom i", IndicadorCustom.class).getResultList();
		List<IndicadorCustom> indicadoresCompletos = indicadoresEnBase.stream().map(indicadorIncompleto -> buildercito.generarCalculo(indicadorIncompleto))
				.collect(Collectors.toList());
		indicadoresCargados.addAll(indicadoresCompletos);
	}
	
	public IndicadorCustom crearIndicador(String ecuacion) {
		return new BuilderIndicadorCustom(ecuacion).analizar().setEcuacion().setCalculo().build();
	}
	
}