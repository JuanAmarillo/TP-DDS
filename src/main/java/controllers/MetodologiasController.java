package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import domain.Empresa;
import domain.metodologias.Metodologia;
import domain.repositorios.RepositorioEmpresas;
import domain.repositorios.RepositorioMetodologias;
import domain.repositorios.RepositorioUsuarios;
import domain.login.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController {
	public static ModelAndView get(Request req, Response res) {
		Usuario usuario = req.session().attribute("usuario");
		Map<String, List<Metodologia>> model = new HashMap<>();
		List<Metodologia> metodologias = RepositorioMetodologias.instance().getElementosDelUsuarioID(usuario.id());
		model.put("metodologias", metodologias);
		return new ModelAndView(model, "proyectos/metodologias.hbs");
	}
	
	public static ModelAndView aplicarMetodologia(Request req, Response res){
		String nombreMetodologia = req.queryParams("metodologia");
		Optional<Metodologia> metodologia = RepositorioMetodologias.instance().findByName(nombreMetodologia);
		List<Empresa> empresasCargadas = RepositorioEmpresas.instance().getElementos();
		List<Empresa> empresasOrdenadas = metodologia.get().aplicarCondiciones(empresasCargadas);
		Map<String, List<Empresa>> model = new HashMap<>();
		model.put("empresasOrdenadas",empresasOrdenadas);
		return new ModelAndView(model, "proyectos/metodologias.hbs");
	}

}