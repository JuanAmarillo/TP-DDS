package controllers;

import controllers.builders.BuilderControllerIndicador;
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

}
