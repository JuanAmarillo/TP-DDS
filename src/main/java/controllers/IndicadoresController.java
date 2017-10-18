package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import domain.repositorios.RepositorioIndicadores;

public class IndicadoresController {
	public static ModelAndView get(Request req, Response res){
		return new ModelAndView(null,"proyectos/indicadores.hbs");
	}
	
	public static ModelAndView nuevo(Request req, Response res){
		return new ModelAndView(null,"proyectos/newIndicador.hbs");
	}
	
	public static ModelAndView agregar(Request req, Response res){
		String indicadorNuevo = req.queryParams("nuevo-indicador");
		RepositorioIndicadores.instance().agregarIndicadorAPartirDel(indicadorNuevo);
		res.redirect("/");
		return null;
	}

}
