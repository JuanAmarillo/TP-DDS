package domain.repositorios;

import java.util.List;

import domain.login.Usuario;
import domain.metodologias.Metodologia;

public class RepositorioMetodologias extends Repositorio<Metodologia> {

	private static RepositorioMetodologias instance = null;

	public static RepositorioMetodologias instance() {
		if (noHayInstanciaCargada())
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		instance = new RepositorioMetodologias();
	}

	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}

	public static void resetSingleton() {
		instance = null;
	}
	
	@Override
	protected Class<Metodologia> getEntity() {
		return Metodologia.class;
	}
}
