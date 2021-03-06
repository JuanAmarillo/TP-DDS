package domain.repositorios;

import java.util.Optional;

import domain.login.Usuario;

public class RepositorioUsuarios extends Repositorio<Usuario> {
	
	private static RepositorioUsuarios instance = null;

	public static RepositorioUsuarios instance() {
		if (noHayInstanciaCargada())
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		instance = new RepositorioUsuarios();
	}

	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}

	public static void resetSingleton() {
		instance = null;
	}

	public Optional<Usuario> encontrarUsuario(String nombreCuenta){
		return find("nombreCuenta", nombreCuenta);
	}
	
	@Override
	protected Class<Usuario> getEntity() {
		return Usuario.class;
	}

}
