package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CondicionesController {

	public static ModelAndView get(Request req, Response res) {
		return new ModelAndView(null, "proyectos/condiciones.hbs");
	}

	public static ModelAndView nuevo(Request req, Response res) {
		return new ModelAndView(null, "proyectos/newCondicion.hbs");
	}

}
