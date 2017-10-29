package controllers;



import persistencia.TransactionManager;
import spark.Request;
import spark.Response;

public class FiltersController {
	public static void before(Request req, Response res){
		String verbo = req.requestMethod();
		if(!verbo.equals("GET")) {
			TransactionManager.instance().crearTransaccion();
			req.session().attribute("utilizaTransac", true);
		}
	}
	
	public static void after(Request req, Response res){
		boolean utilizaTran= req.attribute("utilizaTransac");
		if(utilizaTran) {
			TransactionManager.instance().cerrarTransaccion();
			req.session().removeAttribute("utilizaTransac");
		}
	}
	
	public static void estaLogeado(Request req, Response res){
		if(null == req.session().attribute("usuario")) { 
			res.redirect("/html/login.html");
		}
	}
}
