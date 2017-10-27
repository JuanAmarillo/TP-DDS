package domain.login;

import java.util.Optional;

import domain.repositorios.RepositorioUsuarios;

public class Authenticator {
	
	public static Usuario login(String nombreCuenta, String Password) {
		Optional<Usuario> usuario = RepositorioUsuarios.instance().encontrarUsuario(nombreCuenta);
		if(!usuario.isPresent() ) {
			throw new RuntimeException();
		}
		
		if (!usuario.get().getPassword().equals(Password)) { }
		return usuario.get();
	}
}
