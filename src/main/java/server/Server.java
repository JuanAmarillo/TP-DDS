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
		TransactionManager.instance().crearTransaccion();
		Usuario pepito = new Usuario("pepito", "login", "password");
		RepositorioUsuarios.instance().agregar(pepito);
		TransactionManager.instance().cerrarTransaccion();
		TransactionManager.instance().crearTransaccion();
		RepositorioIndicadores.instance().agregarPredeterminados(pepito);;
		RepositorioCondiciones.instance();
		TransactionManager.instance().cerrarTransaccion();
		/*Spark.port(9000);
		DebugScreen.enableDebugScreen();
		Router.configure();*/
		new InversionesApp().start();
	}

}
