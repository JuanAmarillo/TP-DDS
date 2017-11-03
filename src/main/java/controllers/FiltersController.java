package controllers;



import persistencia.TransactionManager;
import spark.Request;
import spark.Response;

public class FiltersController {
	public static void before(Request req, Response res){
		String verbo = req.requestMethod();
		if(!verbo.equals("GET")) {
			TransactionManager.instance().crearTransaccion();
			req.session().attribute("utilizaTransac", "ye");
		}
		TransactionManager.instance().crearTransaccion();
	}
	
	public static void after(Request req, Response res){
		String utilizaTran= req.session().attribute("utilizaTransac");
		if(utilizaTran != null) {
			TransactionManager.instance().cerrarTransaccion();
			req.session().removeAttribute("utilizaTransac");
		}
		TransactionManager.instance().cerrarTransaccion();
	}
	
	public static void estaLogeado(Request req, Response res){
		
		if(null == req.session().attribute("usuario") ) { 
			res.redirect("/html/login.html");
		}
	}
}
