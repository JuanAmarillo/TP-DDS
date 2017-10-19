package controllers;

import java.util.Set;

import domain.Cuenta;
import domain.repositorios.RepositorioEmpresas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController extends EmpresasPeriodosController {
	
	@Override
	public ModelAndView ModelAndViewEmpresas() {
		return new ModelAndView(model,"proyectos/cuentas.hbs");
	}
	
	@Override
	public ModelAndView ModelAndViewPeriodos() {
		return new ModelAndView(model,"proyectos/selectores/select-periodos.hbs" );
	}
	
	@Override
	public ModelAndView mostrarTabla(Request req,Response res){
		String empresa = req.queryParams("empresa");
		String periodo = req.queryParams("periodo");
		Set<Cuenta> cuentas = RepositorioEmpresas.instance().findByName(empresa).get().getCuentasSegun(periodo);
		model.put("cuentas", cuentas);
		return new ModelAndView(model, "proyectos/cuentas.hbs") ;
	}
	
	

}