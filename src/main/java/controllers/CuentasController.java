package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import domain.Cuenta;
import domain.Empresa;
import domain.repositorios.RepositorioEmpresas;
import persistencia.LevantaArchivoEmpresa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController {
	private static Map<String,Object> model = new HashMap<>();
	
	public static ModelAndView get(Request req, Response res){
		cargarEmpresas();
		List<Empresa> empresas = RepositorioEmpresas.instance().getElementos();
		model.put("empresas", empresas);
		return new ModelAndView(model,"proyectos/cuentas.hbs");
	}
	

	private static void cargarEmpresas(){
		try {
			new LevantaArchivoEmpresa("/home/juan/Documentos/programacion/workspace/Java/2017-jm-group-04/src/test/resources/Coca-Cola.json").cargarArchivo();
			new LevantaArchivoEmpresa("/home/juan/Documentos/programacion/workspace/Java/2017-jm-group-04/src/test/resources/LG.json").cargarArchivo();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}


	public static ModelAndView elegirPeriodo(Request req,Response res){
		String empresa = req.queryParams("empresa");
		Set<String> periodos = RepositorioEmpresas.instance().findByName(empresa).get().getPeriodos();
		model.put("periodos", periodos);
		return new ModelAndView(model,"proyectos/selectores/select-periodos.hbs" );
		
	}
	
	public static ModelAndView mostrarCuentas(Request req,Response res){
		String empresa = req.queryParams("empresa");
		String periodo = req.queryParams("periodo");
		Set<Cuenta> cuentas = RepositorioEmpresas.instance().findByName(empresa).get().getCuentasSegun(periodo);
		model.put("cuentas", cuentas);
		return new ModelAndView(model, "proyectos/cuentas.hbs") ;
	}
	
	

}