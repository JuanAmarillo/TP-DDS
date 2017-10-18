package controllers;

import persistencia.TransactionManager;
import spark.Request;
import spark.Response;

public class FiltersController {
	public static void before(Request req, Response res){
		TransactionManager.instance().crearTransaccion();
	}
	
	public static void after(Request req, Response res){
		TransactionManager.instance().cerrarTransaccion();
	}
	
	public static void estaLogeado(Request req, Response res){
		if(null == req.session().attribute("usuario")) { 
			req.session().attribute("RedirectLuegoDelLogin", req.pathInfo());
			res.redirect("/html/login.html");
		}
	}
}
