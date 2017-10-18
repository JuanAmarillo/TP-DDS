package server;

import controllers.*;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder.create().withDefaultHelpers().build();

		Spark.staticFiles.location("/public");
		
		filters();
		views(engine);

	}

	public static void views(HandlebarsTemplateEngine engine) {
		generalViews(engine);
		cuentasViews(engine);
		indicadoresViews(engine);
		metodologiasViews(engine);
	}

	public static void metodologiasViews(HandlebarsTemplateEngine engine) {
		Spark.get("/proyecto/metodologias", MetodologiasController::get, engine);
	}

	public static void generalViews(HandlebarsTemplateEngine engine) {
		Spark.get("/", HomeController::home, engine);
		Spark.post("/login", LoginController::loguearse, engine);
	}

	public static void filters() {
		Spark.before(FiltersController::before);
		Spark.before("/proyecto/*", FiltersController::estaLogeado);
		Spark.after(FiltersController::after);
	}

	public static void indicadoresViews(HandlebarsTemplateEngine engine) {
		Spark.get("/proyecto/indicadores", IndicadoresController::get, engine);
		Spark.get("/proyecto/indicadores/new", IndicadoresController::nuevo, engine);
		Spark.post("/proyecto/indicadores/new", IndicadoresController::agregar, engine);
	}

	public static void cuentasViews(HandlebarsTemplateEngine engine) {
		CuentasController controller = new CuentasController();
		Spark.get("/proyecto/cuentas", controller::getEmpresas, engine);
		Spark.post("/proyecto/cuentas",controller::mostrarTabla,engine);
		Spark.post("proyecto/cuentas/periodos", controller::elegirPeriodo, engine);
	}

}
