package server;

import controllers.CuentasController;
import controllers.HomeController;
import controllers.IndicadoresController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder.create().withDefaultHelpers().build();

		Spark.staticFiles.location("/public");
		
		Spark.get("/", HomeController::home,engine);
		Spark.get("/cuentas", CuentasController::get,engine);
		Spark.get("/indicadores", IndicadoresController::get,engine);
		Spark.get("/indicadores/new",IndicadoresController::nuevo,engine);
		
	}

}
