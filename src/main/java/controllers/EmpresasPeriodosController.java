package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import domain.Empresa;
import domain.repositorios.RepositorioEmpresas;
import persistencia.LevantaArchivoEmpresa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public abstract class EmpresasPeriodosController {
	protected Map<String,Object> model = new HashMap<>();
	
	public ModelAndView getEmpresas(Request req, Response res){
		cargarEmpresas();
		List<Empresa> empresas = RepositorioEmpresas.instance().getElementos();
		model.put("empresas", empresas);
		return ModelAndViewEmpresas();
	}
	

	private void cargarEmpresas(){
		try {
			new LevantaArchivoEmpresa("/home/juan/Documentos/programacion/workspace/Java/2017-jm-group-04/src/test/resources/Coca-Cola.json").cargarArchivo();
			new LevantaArchivoEmpresa("/home/juan/Documentos/programacion/workspace/Java/2017-jm-group-04/src/test/resources/LG.json").cargarArchivo();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}


	public ModelAndView elegirPeriodo(Request req,Response res){
		String empresa = req.queryParams("empresa");
		Set<String> periodos = RepositorioEmpresas.instance().findByName(empresa).get().getPeriodos();
		model.put("periodos", periodos);
		return ModelAndViewPeriodos();
		
	}
	
	public abstract  ModelAndView ModelAndViewEmpresas();
	public abstract  ModelAndView ModelAndViewPeriodos();
	public abstract  ModelAndView mostrarTabla(Request req,Response res);
	
}
