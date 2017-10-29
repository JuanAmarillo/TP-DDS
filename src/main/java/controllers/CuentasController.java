package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import domain.Cuenta;
import domain.Empresa;
import domain.login.Usuario;
import domain.repositorios.RepositorioEmpresas;
import persistencia.LevantaArchivoEmpresa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController extends EmpresasPeriodosController {
	
	@Override
	public String ruta(){
		return "proyectos/cuentas.hbs";
	}

	
	@Override
	public void agregarAlModel(Empresa empresa,String periodo, Map<String,Object> model, Usuario usuario){
		Set<Cuenta> cuentas = empresa.getCuentasSegun(periodo);
		model.put("cuentas", cuentas);
	}

	
	

}