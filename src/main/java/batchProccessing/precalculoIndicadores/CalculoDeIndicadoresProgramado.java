package batchProccessing.precalculoIndicadores;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import domain.Empresa;
import domain.indicadores.Indicador;
import domain.repositorios.RepositorioIndicadores;
import domain.repositorios.RepositorioIndicadoresCalculados;
import persistencia.Transaction;

public class CalculoDeIndicadoresProgramado implements Job {
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Transaction.instance().crear();
		ejecutarProceso();
		Transaction.instance().cerrar();
	}

	public void ejecutarProceso() {
		List<Indicador> indicadores = RepositorioIndicadores.instance().getElementos();
		List<EmpresaPeriodoARecalcular> empresasARecalcular = ContenedorValoresARecalcular.instance().getList();
		indicadores.stream().forEach(indicador -> calcularIndicador(indicador, empresasARecalcular));
		ContenedorValoresARecalcular.instance().borrarEntradas();
	}

	public void calcularIndicador(Indicador it, List<EmpresaPeriodoARecalcular> empresasARecalcular) {
		empresasARecalcular.stream().forEach(empresa -> agregarAlRepositorio(empresa.getEmpresa(), empresa.getPeriodo() , it));
	}

	public void agregarAlRepositorio(Empresa empresa, String periodo, Indicador indicador) {
		RepositorioIndicadoresCalculados.instance().agregarValores(indicador,empresa, periodo);	
	}
}
