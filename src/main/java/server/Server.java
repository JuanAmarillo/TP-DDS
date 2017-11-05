package server;

import domain.login.Usuario;
import domain.repositorios.RepositorioCondiciones;
import domain.repositorios.RepositorioIndicadores;
import domain.repositorios.RepositorioUsuarios;
import persistencia.Transaction;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		Spark.port(9000);
		DebugScreen.enableDebugScreen();
		Router.configure();
	}

}
