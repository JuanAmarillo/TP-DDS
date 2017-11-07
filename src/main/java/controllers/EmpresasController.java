package controllers;

import controllers.builders.BuilderController;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EmpresasController {
	public static ModelAndView get(Request req, Response res){
		return new BuilderController(req, res).empresas().build("proyectos/empresas.hbs");
	}
}
