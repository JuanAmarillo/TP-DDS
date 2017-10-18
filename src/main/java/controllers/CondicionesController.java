package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CondicionesController {
	public static ModelAndView nuevo(Request req, Response res){
		return new ModelAndView(null,"proyectos/newCondicion.hbs");
	}
	/*
	public static ModelAndView agregar(Request req, Response res){
		String condicionNueva = req.queryParams("nueva-condicion");
		RepositorioCondiciones.instance().agregarCondicionAPartirDel(condicionNueva);
		res.redirect("/");
		return null;
	}
*/
}
