package controllers.builders;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import cacheIndicadores.ConseguidorDeValores;
import domain.Cuenta;
import domain.Empresa;
import domain.indicadores.IndicadorCalculado;
import domain.login.Usuario;
import domain.repositorios.RepositorioIndicadores;
import spark.Request;
import spark.Response;

public class BuilderControllerIndicador extends BuilderController {

	public BuilderControllerIndicador(Request req, Response res) {
		super(req, res);
	}

	public BuilderControllerIndicador indicadoresCalculados() {
		List<IndicadorCalculado> indicadoresCalculados = calcularIndicadoresDe(getEmpresa(), getPeriodo(),
				getUsuario());
		model.put("indicadores", indicadoresCalculados);
		return this;
	}

	private List<IndicadorCalculado> calcularIndicadoresDe(Empresa empresa, String periodo, Usuario usuario) {
		return RepositorioIndicadores.instance().getElementosDelUsuarioID(usuario.id()).stream()
				.map(indicador -> ConseguidorDeValores.get(indicador, empresa, periodo)).collect(Collectors.toList());
	}
}

// indicador.calcular(empresa, periodo)
