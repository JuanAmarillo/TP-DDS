package domain.repositorios;

import java.util.NoSuchElementException;
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
		//instance.agregarPredeterminados();
	}

	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}

	public static void resetSingleton() {
		instance = null;
	}
	
	public Boolean login(String nombreCuenta, String Password) {
		//Encriptar pw
		//Buscar fila
		try {
			Usuario usuario = encontrarUsuario(nombreCuenta).get();
			if(!usuario.getPassword().equals(Password)) {
				throw new RuntimeException("Contraseña incorrecta");
			}
			return true;
		}
		catch(NoSuchElementException e) { throw new RuntimeException("No existe la cuenta"); }
	}

	public Optional<Usuario> encontrarUsuario(String nombreCuenta){
		return find("nombreCuenta", nombreCuenta);
	}
	
	@Override
	protected Class<Usuario> getEntity() {
		return Usuario.class;
	}

}
