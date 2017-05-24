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

	public static List<Indicador> getIndicadoresCargados() {
		return indicadoresCargados;
	}

	public void agregarIndicador(Indicador indicador) {
		getIndicadoresCargados().add(indicador);
	}

	public Double getValorDelIndicador(Empresa empresa, String indicador) {
		Indicador indicadorBuscado = buscarIndicador(indicador);
		return new AnalizadorDeIndicadores().scan(indicadorBuscado).parser(empresa);
	}
	
	private Indicador buscarIndicador(String indicador) {
		return getIndicadoresCargados().stream().filter(unIndicador -> unIndicador.suNombreEs(indicador)).findFirst()
				.get();
	}

}
