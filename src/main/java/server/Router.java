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
		cuentasViews(engine);
		loginViews(engine);
		indicadoresViews(engine);
		metodologiasViews(engine);
	}

	private static void loginViews(HandlebarsTemplateEngine engine) {
		Spark.get("/login", LoginController::get, engine);
		Spark.post("/login", LoginController::loguearse, engine);
	}

	public static void metodologiasViews(HandlebarsTemplateEngine engine) {
		Spark.get("/metodologias", MetodologiasController::get, engine);
		Spark.get("/metodologias/tabla", MetodologiasController::aplicarMetodologia, engine);
	}
         
	public static void filters() {
		Spark.before(FilterTransactionController::before);
		Spark.after(FilterTransactionController::after);
		Spark.before("/*", FilterLoginController::estaLogeado);
	}

	public static void indicadoresViews(HandlebarsTemplateEngine engine) {
		IndicadoresController controller = new IndicadoresController();
		Spark.get("/indicadores", controller::getEmpresas, engine);
		Spark.post("/indicadores/tabla", controller::mostrarTabla,engine);
		Spark.post("/indicadores", controller::agregar, engine);
	}
	
	public static void cuentasViews(HandlebarsTemplateEngine engine) {
		CuentasController controller = new CuentasController();
		Spark.get("/cuentas", controller::getEmpresas, engine);
		Spark.post("/cuentas/tabla", controller::mostrarTabla, engine);
		Spark.post("/periodos", controller::elegirPeriodo, engine);
	}

}
