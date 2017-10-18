package server;

import controllers.*;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder.create().withDefaultHelpers().build();

		Spark.staticFiles.location("/public");

		Spark.before(FiltersController::before);
		Spark.get("/", HomeController::home, engine);
		Spark.get("/cuentas", CuentasController::get, engine);
		Spark.get("/indicadores", IndicadoresController::get, engine);
		Spark.get("/indicadores/new", IndicadoresController::nuevo, engine);
		Spark.post("/indicadores/new", IndicadoresController::agregar, engine);
		Spark.get("/condiciones", CondicionesController::get, engine);
		Spark.get("/condiciones/new", CondicionesController::nuevo, engine);
		Spark.get("/metodologias", MetodologiasController::get, engine);
		Spark.after(FiltersController::after);

	}

}
