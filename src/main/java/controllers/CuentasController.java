package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController {
	public static ModelAndView get(Request req, Response res){
		return new ModelAndView(null,"proyectos/cuentas.hbs");
	}

}