package domain.login;

import java.util.Optional;

import domain.repositorios.RepositorioUsuarios;

public class Authenticator {
	
	public static void login(String nombreCuenta, String Password) {
		Optional<Usuario> usuario = RepositorioUsuarios.instance().encontrarUsuario(nombreCuenta);
		if(!usuario.isPresent() || !usuario.get().getPassword().equals(Password)) {
			throw new RuntimeException();
		}
	}
}
