package server;


import org.quartz.JobExecutionException;

import batchProccessing.Planificador;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) throws JobExecutionException {
		Spark.port(9000);
		DebugScreen.enableDebugScreen();
		Router.configure();
		Planificador.instance().begin();
	}
}
