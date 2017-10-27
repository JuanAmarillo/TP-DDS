package server;

import application.InversionesApp;
import domain.login.Usuario;
import domain.repositorios.RepositorioCondiciones;
import domain.repositorios.RepositorioIndicadores;
import domain.repositorios.RepositorioUsuarios;
import persistencia.TransactionManager;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		/*TransactionManager.instance().crearTransaccion();
		RepositorioUsuarios.instance().agregar(new Usuario("pepito", "login", "password"));
		TransactionManager.instance().cerrarTransaccion();
		RepositorioIndicadores.instance();
		RepositorioCondiciones.instance();*/
		Spark.port(9000);
		DebugScreen.enableDebugScreen();
		Router.configure();
		new InversionesApp().start();
	}

}
