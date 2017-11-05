package controllers;

import controllers.builders.BuilderController;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public abstract class EmpresasPeriodosController {

	public ModelAndView getEmpresas(Request req, Response res) {
		return new BuilderController(req, res).empresas().build(ruta());
	}

	public ModelAndView elegirPeriodo(Request req, Response res) {
		return new BuilderController(req, res).periodos().build("proyectos/selectores/select-periodos.hbs");
	}

	public abstract String ruta();

	public abstract ModelAndView mostrarTabla(Request req, Response res);
}