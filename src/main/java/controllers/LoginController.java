package controllers;

import domain.login.Authenticator;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;

public class LoginController {
	
	public static ModelAndView loguearse(Request req, Response res) {
		String usuario = req.queryParams("user");
		String password = req.queryParams("password");
		try {
			Authenticator.login(usuario, password);
		}
		catch(RuntimeException e) {
			Spark.halt(401, "No esta logueado en el sistema.");
		}
		return new ModelAndView(null, "home/home.hbs");
	}
	
}
