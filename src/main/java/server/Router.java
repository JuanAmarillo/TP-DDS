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
		Spark.before("/proyecto/*", FiltersController::estaLogeado);
		Spark.get("/", HomeController::home, engine);
		Spark.post("/login", LoginController::loguearse, engine);
		Spark.get("/proyecto/cuentas", CuentasController::get, engine);
		Spark.post("/proyecto/cuentas",CuentasController::mostrarCuentas,engine);
		Spark.post("proyecto/cuentas/periodos", CuentasController::elegirPeriodo, engine);
		Spark.get("/proyecto/indicadores", IndicadoresController::get, engine);
		Spark.get("/proyecto/indicadores/new", IndicadoresController::nuevo, engine);
		Spark.post("/proyecto/indicadores/new", IndicadoresController::agregar, engine);
		Spark.get("/proyecto/metodologias", MetodologiasController::get, engine);
		Spark.after(FiltersController::after);

	}

}
