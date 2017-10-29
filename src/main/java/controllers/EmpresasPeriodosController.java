package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import domain.Empresa;
import domain.login.Usuario;
import domain.repositorios.RepositorioEmpresas;
import domain.repositorios.RepositorioIndicadores;
import persistencia.LevantaArchivoEmpresa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public abstract class EmpresasPeriodosController {
	
	public ModelAndView getEmpresas(Request req, Response res){
		Map<String,Object> model = new HashMap<>();
		List<Empresa> empresas = RepositorioEmpresas.instance().getElementos();
		model.put("empresas", empresas);
		return modelAndView(model);
	}

	public ModelAndView elegirPeriodo(Request req,Response res){
		Map<String,Object> model = new HashMap<>();
		String empresa = req.queryParams("empresa");
		Set<String> periodos = RepositorioEmpresas.instance().findByName(empresa).get().getPeriodos();
		model.put("periodos", periodos);
		return new ModelAndView(model,"proyectos/selectores/select-periodos.hbs" );
		
	}
	public ModelAndView mostrarTabla(Request req, Response res){
		Map<String,Object> model = new HashMap<>();
		String empresa = req.queryParams("empresa");
		String periodo = req.queryParams("periodo");
		Usuario usuario = req.session().attribute("usuario");
		agregarAlModel(RepositorioEmpresas.instance().findByName(empresa).get(),periodo,model, usuario);
		return modelAndView(model);
	}
	
	public ModelAndView modelAndView(Map<String,Object> model) {
		return new ModelAndView(model,ruta());
	}
	
	public abstract String ruta();
	public abstract void agregarAlModel(Empresa empresa,String periodo, Map<String,Object> model, Usuario usuario);
	
}
