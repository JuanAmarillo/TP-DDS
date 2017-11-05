package controllers.builders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import domain.Empresa;
import domain.login.Usuario;
import domain.repositorios.RepositorioEmpresas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class BuilderController {
	protected Map<String,Object> model = new HashMap<>();
	protected Request request;
	protected Response response;
	private String ruta;
	
	public BuilderController(Request req, Response res){
		this.request = req;
		this.response = res;
	}
	
	public BuilderController empresas(){
		List<Empresa> empresas = RepositorioEmpresas.instance().getElementos();
		model.put("empresas", empresas);
		return this;
	}
	
	public BuilderController periodos() {
		Set<String> periodos = getEmpresa().getPeriodos();
		model.put("periodos", periodos);
		return this;
	}
	
	protected Empresa getEmpresa() {
		String empresa = request.queryParams("empresa");
		return RepositorioEmpresas.instance().findByName(empresa).get();
	}
	
	protected String getPeriodo() {
		return request.queryParams("periodo");
	}
	
	protected Usuario getUsuario(){
		return request.session().attribute("usuario");
	}
	
	public ModelAndView build(String ruta){
		return new ModelAndView(model,ruta);		
	}
	
	
}
