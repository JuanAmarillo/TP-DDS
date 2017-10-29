package controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.Empresa;
import domain.indicadores.IndicadorCalculado;
import domain.indicadores.IndicadorCustom;
import domain.indicadores.calculoIndicadores.Numero;
import domain.login.Usuario;
import domain.repositorios.RepositorioIndicadores;

public class IndicadoresController extends EmpresasPeriodosController {

	@Override
	public String ruta() {
		return "proyectos/indicadores.hbs";
	}

	@Override
	public void agregarAlModel(Empresa empresa, String periodo, Map<String,Object> model, Usuario usuario) {
		List<IndicadorCalculado> indicadoresCalculados = RepositorioIndicadores.instance()
				.getElementosDelUsuarioID(usuario.id()).stream().map(indicador -> indicador.calcular(empresa, periodo))
				.collect(Collectors.toList());
		model.put("indicadores", indicadoresCalculados);
	}

}
