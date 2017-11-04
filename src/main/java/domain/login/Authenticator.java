package domain.login;
import java.util.Optional;
import domain.repositorios.RepositorioUsuarios;
import exceptions.LoginException;

public class Authenticator {
	
	public static Usuario login(String nombreCuenta, String Password) {
		Optional<Usuario> usuario = RepositorioUsuarios.instance().encontrarUsuario(nombreCuenta);
		verificarUsuario(usuario);
		verificarPassword(Password, usuario);
		return usuario.get();
	}

	private static void verificarPassword(String Password, Optional<Usuario> usuario) {
		if(esPasswordValida(Password, usuario)) 
			throw new LoginException("Credenciales invalidas");
		
	}

	private static void verificarUsuario(Optional<Usuario> usuario) {
		if(existeElUsuario(usuario)) 
			throw new LoginException("El usuario no existe");
		
	}

	private static boolean esPasswordValida(String Password, Optional<Usuario> usuario) {
		return !usuario.get().getPassword().equals(Password);
	}

	private static boolean existeElUsuario(Optional<Usuario> usuario) {
		return !usuario.isPresent();
	}
}
