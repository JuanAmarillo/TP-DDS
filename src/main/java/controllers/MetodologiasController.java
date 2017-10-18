package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.metodologias.Metodologia;
import domain.repositorios.RepositorioMetodologias;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController {
	public static ModelAndView get(Request req, Response res){
		Map<String, List<Metodologia>> model = new HashMap<>();
		List<Metodologia> metodologias = RepositorioMetodologias.instance().getElementosDe(Metodologia.class);
		model.put("metodologias", metodologias);
		return new ModelAndView(model,"proyectos/metodologias.hbs");
	}

}