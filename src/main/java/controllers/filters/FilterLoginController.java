package controllers.filters;

import spark.Request;
import spark.Response;

public class FilterLoginController {
	
	public static void estaLogeado(Request req, Response res){
		if(noHayUsuarioActivo(req) && noEstaEnPantallaLogin(req)) 
			res.redirect("/html/login.html");
	}

	private static boolean noEstaEnPantallaLogin(Request req) {
		return !req.pathInfo().equals("/login");
	}


	private static boolean noHayUsuarioActivo(Request req) {
		return null == req.session().attribute("usuario");
	}
}
