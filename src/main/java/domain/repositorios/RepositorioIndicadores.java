package domain.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.Empresa;
import domain.indicadores.IndicadorCustom;
import exceptions.NoSePuedeBorrarUnPredeterminadoException;
import interfaces.Indicador;
import externos.AnalizadorDeIndicadores;

public class RepositorioIndicadores {
	private static List<Indicador> indicadoresCargados;
	private static RepositorioIndicadores instance = null;

	public static RepositorioIndicadores instance() {
		if (instance == null) {
			indicadoresCargados = new ArrayList<Indicador>();
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
	
	public List<Indicador> obtenerCustoms() {
		List<Indicador> lista = new ArrayList<Indicador>();
		lista.addAll(indicadoresCargados.stream().filter(ind -> ind.esCustom()).collect(Collectors.toList()));
		return lista;
	}

	public void agregarIndicador(IndicadorCustom indicador) {
		RepositorioIndicadores.instance().getIndicadoresCargados().add(indicador);
	}
	
	public void agregarIndicadores(List<Indicador> indicadores) {
		indicadoresCargados.addAll(indicadores);
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

	public Double getValorDelIndicador(Empresa empresa, String indicador,String periodo) {
		Indicador indicadorBuscado = buscarIndicador(indicador);
		// ACA CAMBIA LA FORMA DEL CALCULO DEL INDICADOR
		//return new AnalizadorDeIndicadores(empresa,periodo).scan(indicadorBuscado).parser();
		return indicadorBuscado.calcularIndicador(empresa, periodo);
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

}
