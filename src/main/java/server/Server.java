package server;

import domain.login.Usuario;
import domain.repositorios.RepositorioUsuarios;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		RepositorioUsuarios.instance().agregar(new Usuario("pepito", "login", "password"));
		Spark.port(9000);
		DebugScreen.enableDebugScreen();
		Router.configure();
	}

}
