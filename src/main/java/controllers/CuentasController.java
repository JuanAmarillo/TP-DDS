package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import domain.Cuenta;
import domain.Empresa;
import domain.repositorios.RepositorioEmpresas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController {
	public static ModelAndView get(Request req, Response res){
		List<Empresa> empresas = RepositorioEmpresas.instance().getElementos();
		Map<String,Object> model = new HashMap<>();
		model.put("empresas", empresas);
		return new ModelAndView(model,"proyectos/cuentas/cuentas.hbs");
	}
	
	public static ModelAndView elegirPeriodo(Request req,Response res){
		String empresa = req.queryParams("empresa");
		Set<String> periodos = RepositorioEmpresas.instance().findByName(empresa).get().getPeriodos();
		return new ModelAndView(periodos,"proyectos/cuentas/select-periodos.hbs" );
		
	}
	
	public static ModelAndView mostrarCuentas(Request req,Response res){
		String empresa = req.queryParams("empresa");
		String periodo = req.queryParams("periodo");
		Set<Cuenta> cuentas = RepositorioEmpresas.instance().findByName(empresa).get().getCuentasSegun(periodo);
		Map<String,Set<Cuenta>> model = new HashMap<>();
		model.put("cuentas", cuentas);
		return new ModelAndView(model, "proyectos/cuentas/cuentas.hbs") ;
	}
	
	

}