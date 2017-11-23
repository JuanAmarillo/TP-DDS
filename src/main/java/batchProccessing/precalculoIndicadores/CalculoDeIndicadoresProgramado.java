package batchProccessing.precalculoIndicadores;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import domain.Empresa;
import domain.indicadores.Indicador;
import domain.indicadores.IndicadorCalculado;
import domain.repositorios.RepositorioIndicadores;
import domain.repositorios.RepositorioIndicadoresCalculados;
import persistencia.Transaction;

public class CalculoDeIndicadoresProgramado implements Job {
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Transaction.instance().crear();
		List<Indicador> indicadores = RepositorioIndicadores.instance().getElementos();
		List<EmpresaPeriodoARecalcular> empresasARecalcular = ContenedorValoresARecalcular.instance().getList();
		indicadores.stream().forEach(it -> calcularIndicador(it, empresasARecalcular));
		ContenedorValoresARecalcular.instance().borrarEntradas();
		Transaction.instance().cerrar();
	}

	public void calcularIndicador(Indicador it, List<EmpresaPeriodoARecalcular> empresasARecalcular) {
		empresasARecalcular.stream().forEach(empresa -> calcularLosPeriodos(empresa, it));
	}

	private void calcularLosPeriodos(EmpresaPeriodoARecalcular empresaARecalcular, Indicador it) {
		Empresa empresa = empresaARecalcular.getEmpresa();
		empresa.getPeriodos().stream().forEach(periodo -> agregarAlRepositorio(empresa, periodo, it));
	}

	public void agregarAlRepositorio(Empresa empresa, String periodo, Indicador indicador) {
		RepositorioIndicadoresCalculados.instance().agregarValores(indicador.calcular(empresa, periodo));
	}
}
