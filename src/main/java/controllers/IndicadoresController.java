package controllers;

import controllers.builders.BuilderControllerIndicador;
import domain.indicadores.IndicadorCustom;
import domain.login.Usuario;
import domain.repositorios.RepositorioIndicadores;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresController extends EmpresasPeriodosController {

	@Override
	public String ruta() {
		return "proyectos/indicadores.hbs";
	}

	@Override
	public ModelAndView mostrarTabla(Request req, Response res) {
		return new BuilderControllerIndicador(req, res).indicadoresCalculados().empresas().periodos().build(ruta());
	}
	
	public IndicadorCustom crearIndicador(String indicador) {
		return RepositorioIndicadores.instance().crearIndicador(indicador);
	}
	
	public ModelAndView agregar(Request req, Response res){
		String indicadorNuevo = req.queryParams("nuevoIndicador");
		RepositorioIndicadores.instance().agregarIndicadorAPartirDel(indicadorNuevo);
		Usuario usuarioConNuevoIndicador = req.session().attribute("usuario");
		usuarioConNuevoIndicador.agregarIndicador(crearIndicador(indicadorNuevo));
		res.redirect("/indicadores");
		return null;
	}

}
