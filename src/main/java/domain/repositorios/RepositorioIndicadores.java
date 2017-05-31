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
	
	public void agregarIndicador(Indicador indicador){
		RepositorioIndicadores.instance().getIndicadoresCargados().add(indicador);
	}
	
	private void validarIndicador(Indicador indicador){
		indicador.ecuacionContieneAlNombre();
		indicadorExistente(indicador);
		new AnalizadorDeIndicadores(null).scan(indicador).parser();
	}
	
	private void indicadorExistente(Indicador indicador) {
		if(contieneElIndicador(indicador.nombre))
			throw new RuntimeException("El indicador ya existe");
	}
	

	public Double getValorDelIndicador(Empresa empresa, String indicador) {
		Indicador indicadorBuscado = buscarIndicador(indicador);
		return new AnalizadorDeIndicadores(empresa).scan(indicadorBuscado).parser();
	}
	
	public Indicador buscarIndicador(String nombre) {
		return getIndicadoresCargados().stream().filter(unIndicador -> unIndicador.suNombreEs(nombre)).findFirst()
				.get();
	}

	public boolean contieneElIndicador(String nombre) {
		return getIndicadoresCargados().stream().anyMatch(unIndicador -> unIndicador.suNombreEs(nombre));
	}

	public List<String> getNombresDeIndicadores() {
		return getIndicadoresCargados().stream().map(unIndicador->unIndicador.nombre).collect(Collectors.toList());
	}

}
