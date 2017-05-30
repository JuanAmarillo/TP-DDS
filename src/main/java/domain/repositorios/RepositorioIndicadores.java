package domain.repositorios;

import java.util.ArrayList;
import java.util.List;

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
	
	public void agregarIndicador(Indicador indicador) {
		RepositorioIndicadores.getIndicadoresCargados().add(indicador);
	}
	
	public static List<Indicador> getIndicadoresCargados() {
		return indicadoresCargados;
	}

	public Double getValorDelIndicador(Empresa empresa, String indicador) {
		Indicador indicadorBuscado = buscarIndicador(indicador);
		return new AnalizadorDeIndicadores(empresa).scan(indicadorBuscado).parser();
	}
	
	private Indicador buscarIndicador(String indicador) {
		return getIndicadoresCargados().stream().filter(unIndicador -> unIndicador.suNombreEs(indicador)).findFirst()
				.get();
	}

	public boolean contieneElIndicador(String indicador) {
		return getIndicadoresCargados().stream().anyMatch(unIndicador -> unIndicador.suNombreEs(indicador));
	}

}
