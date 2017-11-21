package batchProccessing;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import domain.Empresa;
import domain.indicadores.Indicador;
import domain.repositorios.RepositorioIndicadores;
import domain.repositorios.RepositorioIndicadoresCalculados;

public class CalculoDeIndicadoresProgramado implements Job {
	
	private List<EmpresaPeriodoARecalcular> empresasARecalcular;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		List<Indicador> indicadores = RepositorioIndicadores.instance().getElementos();
		empresasARecalcular = ContenedorValoresARecalcular.instance().getList();
		indicadores.stream().forEach(it -> calcularIndicadores(it));
	}

	private void calcularIndicadores(Indicador it) {
		empresasARecalcular.stream().forEach(empresa -> calcularLosPeriodos(empresa, it));
	}

	private void calcularLosPeriodos(EmpresaPeriodoARecalcular empresaARecalcular, Indicador it) {
		Empresa empresa = empresaARecalcular.getEmpresa();
		empresa.getPeriodos().stream().forEach(periodo -> agregarAlRepositorio(empresa, periodo, it));
	}

	private void agregarAlRepositorio(Empresa empresa, String periodo, Indicador indicador) {
		RepositorioIndicadoresCalculados.instance().agregar(indicador.calcular(empresa, periodo));
	}
}
