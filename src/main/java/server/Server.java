package server;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.quartz.JobExecutionException;

import batchProccessing.precalculoIndicadores.CalculoDeIndicadoresProgramado;
import batchProccessing.precalculoIndicadores.ContenedorValoresARecalcular;
import domain.Cuenta;
import domain.Empresa;
import domain.indicadores.indicadoresPredeterminados.ROE;
import domain.login.Usuario;
import domain.repositorios.RepositorioCondiciones;
import domain.repositorios.RepositorioEmpresas;
import domain.repositorios.RepositorioIndicadores;
import domain.repositorios.RepositorioIndicadoresCalculados;
import domain.repositorios.RepositorioUsuarios;
import persistencia.Transaction;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) throws JobExecutionException {
		/*Spark.port(9000);
		DebugScreen.enableDebugScreen();
		Router.configure();*/
		Transaction.instance().crear();
		Usuario usuario = new Usuario("pept", "ma", "je");
		usuario.agregarIndicador(new ROE());
		RepositorioUsuarios.instance().agregar(usuario);
		Empresa empresa = new Empresa("Manaos");
		empresa.setAnioFundacion(1995);
		Cuenta cuenta = new Cuenta("Beneficio", "Siempre", 10000.0);
		Cuenta cuenta2 = new Cuenta("PatrimonioNeto", "Siempre", 1000.0);
		ContenedorValoresARecalcular.instance().agregarEmpresaPeriodo(empresa, "Siempre");
		empresa.setCuentas(Stream.of(cuenta, cuenta2).collect(Collectors.toSet()));
		RepositorioEmpresas.instance().agregar(empresa);
		CalculoDeIndicadoresProgramado proc = new CalculoDeIndicadoresProgramado();
		proc.execute(null);
		System.out.println("Cantidad calculados" + RepositorioIndicadoresCalculados.instance().cantidadElementosCargados());
		Transaction.instance().cerrar();
	}
}
