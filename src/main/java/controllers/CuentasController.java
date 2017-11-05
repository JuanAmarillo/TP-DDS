package controllers;

import java.util.Map;
import java.util.Set;

import controllers.builders.BuilderController;
import controllers.builders.BuilderControllerCuenta;
import domain.Cuenta;
import domain.Empresa;
import domain.login.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController extends EmpresasPeriodosController {
	
	@Override
	public String ruta(){
		return "proyectos/cuentas.hbs";
	}

	@Override
	public ModelAndView mostrarTabla(Request req, Response res) {
		return new BuilderControllerCuenta(req, res).cuentas().empresas().periodos().build(ruta());
	}

	
	

}