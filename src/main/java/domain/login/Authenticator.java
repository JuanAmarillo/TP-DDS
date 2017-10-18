package domain.login;

import java.util.Optional;

import domain.repositorios.RepositorioUsuarios;

public class Authenticator {
	
	private static Usuario usuarioLogueado;
	
	public static Usuario login(String nombreCuenta, String Password) {
		Optional<Usuario> usuario = RepositorioUsuarios.instance().encontrarUsuario(nombreCuenta);
		if(!usuario.isPresent() || !usuario.get().getPassword().equals(Password)) {
			throw new RuntimeException();
		}
		return usuario.get();
	}

	public static void saveUser(Usuario usuario) {
		usuarioLogueado = usuario;
	}
	
	public static Boolean isLoggedIn() {
		return usuarioLogueado != null;
	}
}
