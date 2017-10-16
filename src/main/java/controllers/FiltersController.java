package controllers;

import domain.repositorios.RepositorioEmpresas;
import spark.Request;
import spark.Response;

public class FiltersController {
	public static void before(Request req, Response res){
		RepositorioEmpresas.instance().crearTransaccion();
	}
	
	public static void after(Request req, Response res){
		RepositorioEmpresas.instance().cerrarTransaccion();
	}
}
