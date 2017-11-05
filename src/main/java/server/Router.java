package server;

import controllers.*;
import controllers.filters.FilterLoginController;
import controllers.filters.FilterTransactionController;
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
		Spark.get("/metodologias", MetodologiasController::get, engine);
		Spark.post("/metodologias", MetodologiasController::aplicarMetodologia, engine);
	}

	public static void generalViews(HandlebarsTemplateEngine engine) {
		Spark.post("/login", LoginController::loguearse, engine);
	}

	public static void filters() {
		Spark.before(FilterTransactionController::before);
		Spark.after(FilterTransactionController::after);
		Spark.before("/*", FilterLoginController::estaLogeado);
	}

	public static void indicadoresViews(HandlebarsTemplateEngine engine) {
		evaluacionDeIndicadores(engine);
		cargaDeNuevosIndicadores(engine);
	}

	public static void cargaDeNuevosIndicadores(HandlebarsTemplateEngine engine) {
		Spark.get("/indicadores/new", IndicadoresNuevosController::nuevo, engine);
		Spark.post("/indicadores/new", IndicadoresNuevosController::agregar, engine);
	}

	public static void evaluacionDeIndicadores(HandlebarsTemplateEngine engine) {
		IndicadoresController controller = new IndicadoresController();
		Spark.get("/indicadores", controller::getEmpresas, engine);
		Spark.post("/indicadores", controller::mostrarTabla,engine);
	}

	public static void cuentasViews(HandlebarsTemplateEngine engine) {
		CuentasController controller = new CuentasController();
		Spark.get("/cuentas", controller::getEmpresas, engine);
		Spark.post("/cuentas", controller::mostrarTabla, engine);
		Spark.post("/periodos", controller::elegirPeriodo, engine);
	}

}
