package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresController {
	public static ModelAndView get(Request req, Response res){
		return new ModelAndView(null,"proyectos/indicadores.hbs");
	}
	
	public static ModelAndView nuevo(Request req, Response res){
		return new ModelAndView(null,"proyectos/newIndicador.hbs");
	}

}
