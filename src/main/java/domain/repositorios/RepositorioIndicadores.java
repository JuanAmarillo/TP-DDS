package domain.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.Empresa;
import domain.Indicador;
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

	public void agregarIndicadorAPartirDe(String indicador) {
		Indicador indicadorACargar = Indicador.armarApartirDe(indicador);
		validarIndicador(indicadorACargar);
		agregarIndicador(indicadorACargar);
	}
	
	public void eliminarIndicadorAPartirDe(String nombre) {
		Indicador indicadorASacar = this.buscarIndicador(nombre);
		eliminarIndicador(indicadorASacar);
	}

	public void eliminarIndicador(Indicador indicador) {
		RepositorioIndicadores.instance().getIndicadoresCargados().remove(indicador);
	}

	public void agregarIndicador(Indicador indicador) {
		RepositorioIndicadores.instance().getIndicadoresCargados().add(indicador);
	}
	
	public void agregarIndicadores(List<Indicador>Indicadores) {
		for(Indicador indicador: Indicadores){
			agregarIndicador(indicador);
		}
			
	}

	private void validarIndicador(Indicador indicador) {
		indicador.ecuacionContieneAlNombre();
		indicadorExistente(indicador);
		new AnalizadorDeIndicadores(null,null).scan(indicador).parser();
	}

	private void indicadorExistente(Indicador indicador) {
		if (contieneElIndicador(indicador.nombre))
			throw new RuntimeException("El indicador ya existe");
	}

	public Double getValorDelIndicador(Empresa empresa, String indicador,String periodo) {
		Indicador indicadorBuscado = buscarIndicador(indicador);
		return new AnalizadorDeIndicadores(empresa,periodo).scan(indicadorBuscado).parser();
	}

	public Indicador buscarIndicador(String nombre) {
		return getIndicadoresCargados().stream().filter(unIndicador -> unIndicador.suNombreEs(nombre)).findFirst()
				.orElseThrow(()-> new RuntimeException("Seleccione un indicador a borrar"));
	}

	public boolean contieneElIndicador(String nombre) {
		return getIndicadoresCargados().stream().anyMatch(unIndicador -> unIndicador.suNombreEs(nombre));
	}

	public List<String> getNombresDeIndicadores() {
		return getIndicadoresCargados().stream().map(unIndicador -> unIndicador.nombre).collect(Collectors.toList());
	}

}
