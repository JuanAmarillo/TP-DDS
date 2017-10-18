package server;

import controllers.*;
import domain.login.Authenticator;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder.create().withDefaultHelpers().build();

		Spark.staticFiles.location("/public");
		
		Spark.before(FiltersController::before);
		Spark.before("/proyecto/*", (request, response) -> {
			if(null == request.session().attribute("usuario")) { 
				response.redirect("/html/login.html");
			}
		});
		Spark.get("/", HomeController::home, engine);
		Spark.post("/login", LoginController::loguearse, engine);
		Spark.get("/proyecto/cuentas", CuentasController::get, engine);
		Spark.get("/proyecto/indicadores", IndicadoresController::get, engine);
		Spark.get("/proyecto/indicadores/new", IndicadoresController::nuevo, engine);
		Spark.get("/proyecto/condiciones", CondicionesController::get, engine);
		Spark.get("/proyecto/condiciones/new", CondicionesController::nuevo, engine);
		Spark.get("/proyecto/metodologias", MetodologiasController::get, engine);
		Spark.after(FiltersController::after);

	}

}
