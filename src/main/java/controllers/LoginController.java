package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.login.Authenticator;
import domain.login.Usuario;
import domain.metodologias.Metodologia;
import exceptions.LoginException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.Session;

public class LoginController {
	
	public static ModelAndView loguearse(Request req, Response res) {
		String username = req.queryParams("user");
		String password = req.queryParams("password");
		return autenticar(req, res, username, password);
	}

	private static ModelAndView autenticar(Request req, Response res, String username, String password) {
		try {
			Usuario usuario = Authenticator.login(username, password);
			req.session().attribute("usuario", usuario);
			res.redirect("/html/home.html");
			return null;
		}
		catch(LoginException e) {
			Map<String, Boolean> model = new HashMap<>();
			model.put("esRelogueo", true);
			return new ModelAndView(model, "proyectos/login.hbs");
		}
	}
	
	public static ModelAndView get(Request req, Response res) {
		return new ModelAndView(null, "proyectos/login.hbs");
	}
}
