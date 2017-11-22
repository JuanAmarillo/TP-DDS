package batchProccessing.cargaDeArchivos;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import persistencia.LevantaArchivoEmpresa;

public class CargaArchivosProgramada implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		new LevantaArchivoEmpresa().execute();
	}
}
