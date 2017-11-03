package controllers;

import domain.login.Authenticator;
import domain.login.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.Session;

public class LoginController {
	
	public static ModelAndView loguearse(Request req, Response res) {
		String username = req.queryParams("user");
		String password = req.queryParams("password");
		try {
			Usuario usuario = Authenticator.login(username, password);
			req.session().attribute("usuario", usuario);
			
		}
		catch(RuntimeException e) {
			e.printStackTrace();
			res.redirect("/html/login.html");
		}
		res.redirect("/html/home.html");
		return null;
	}
}
