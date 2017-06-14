package domain.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.Empresa;
import domain.indicadores.*;
import exceptions.NoSePuedeBorrarUnPredeterminadoException;
import interfaces.Indicador;
import externos.AnalizadorDeIndicadores;
import ui.windows.CalculadorDeIndicador;

public class RepositorioIndicadores {
	private static List<Indicador> indicadoresCargados;
	private static RepositorioIndicadores instance = null;

	public static RepositorioIndicadores instance() {
		if (instance == null) {
			indicadoresCargados = new ArrayList<Indicador>();
			agregarPredeterminados();
			instance = new RepositorioIndicadores();
		}
		return instance;
	}

	public static void resetSingleton() {
		instance = null;
	}

	public List<Indicador> getIndicadoresCargados() {
		return indicadoresCargados;
	}

	public IndicadorCustom agregarIndicadorAPartirDe(String indicador) {
		IndicadorCustom indicadorACargar = IndicadorCustom.armarApartirDe(indicador);
		validarIndicador(indicadorACargar);
		agregarIndicador(indicadorACargar);
		return indicadorACargar;
	}

	public void eliminarIndicadorAPartirDe(String nombre) {
		Indicador indicadorASacar = this.buscarIndicador(nombre);
		eliminarIndicador(indicadorASacar);
	}

	public void eliminarIndicador(Indicador indicador) throws NoSePuedeBorrarUnPredeterminadoException{
		if(indicador.esCustom()) {
			RepositorioIndicadores.instance().getIndicadoresCargados().remove(indicador);
		}
		else {
			throw new NoSePuedeBorrarUnPredeterminadoException();
		}
	}

	public List<IndicadorCustom> obtenerCustoms() {
		List<IndicadorCustom> lista = new ArrayList<IndicadorCustom>();
		lista.addAll(indicadoresCargados.stream().filter(ind -> ind.esCustom()).map(ind -> (IndicadorCustom) ind).collect(Collectors.toList()));
		return lista;
	}

	public void agregarIndicador(IndicadorCustom indicador) {
		RepositorioIndicadores.instance().getIndicadoresCargados().add(indicador);
	}

	public void agregarIndicadores(List<IndicadorCustom> indicadoresADevolver) {
		indicadoresCargados.addAll(indicadoresADevolver);
	}

	private void validarIndicador(IndicadorCustom indicador) {
		indicador.ecuacionContieneAlNombre();
		indicadorExistente(indicador);
		new AnalizadorDeIndicadores(null,null).scan(indicador).parser();
	}

	private void indicadorExistente(IndicadorCustom indicador) {
		if (contieneElIndicador(indicador.nombre))
			throw new RuntimeException("El indicador ya existe");
	}

	public Indicador buscarIndicador(String nombre) {
		return getIndicadoresCargados().stream().filter(unIndicador -> unIndicador.suNombreEs(nombre)).findFirst()
				.orElseThrow(()-> new RuntimeException("Seleccione un indicador a borrar"));
	}

	public boolean contieneElIndicador(String nombre) {
		return getIndicadoresCargados().stream().anyMatch(unIndicador -> unIndicador.suNombreEs(nombre));
	}

	public List<String> getNombresDeIndicadores() {
		return getIndicadoresCargados().stream().map(unIndicador -> unIndicador.getNombre()).collect(Collectors.toList());
	}

	public static void agregarPredeterminados() {
		indicadoresCargados.add(new Pers_Sencillo());
		indicadoresCargados.add(new Pers_SoloNumeros());
	}
	
	public List<CalculadorDeIndicador> generarCalculadores() {
		ArrayList<CalculadorDeIndicador> calculadores = new ArrayList<CalculadorDeIndicador>();
		indicadoresCargados.forEach(indicador -> calculadores.add(new CalculadorDeIndicador(indicador)));
		return calculadores;
	}
}
