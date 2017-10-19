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
		Spark.post("proyecto/metodologias", MetodologiasController::aplicarMetodologia, engine);
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
		evaluacionDeIndicadores(engine);
		cargaDeNuevosIndicadores(engine);
	}

	public static void cargaDeNuevosIndicadores(HandlebarsTemplateEngine engine) {
		Spark.get("/proyecto/indicadores/new", IndicadoresNuevosController::nuevo, engine);
		Spark.post("/proyecto/indicadores/new", IndicadoresNuevosController::agregar, engine);
	}

	public static void evaluacionDeIndicadores(HandlebarsTemplateEngine engine) {
		IndicadoresController controller = new IndicadoresController();
		Spark.get("/proyecto/indicadores", controller::getEmpresas, engine);
		Spark.post("/proyecto/indicadores", controller::mostrarTabla,engine);
	}

	public static void cuentasViews(HandlebarsTemplateEngine engine) {
		CuentasController controller = new CuentasController();
		Spark.get("/proyecto/cuentas", controller::getEmpresas, engine);
		Spark.post("/proyecto/cuentas", controller::mostrarTabla, engine);
		Spark.post("/proyecto/periodos", controller::elegirPeriodo, engine);
	}

}
