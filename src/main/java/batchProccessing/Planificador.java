package batchProccessing;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.listeners.JobChainingJobListener;

import batchProccessing.cargaDeArchivos.CargaArchivosProgramada;
import domain.repositorios.RepositorioIndicadoresCalculados;
import utils.PropertyReader;

public class Planificador {

	public Scheduler scheduler ;
	private static Planificador instance = null;
	
	public Planificador() {
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void begin() {
		try {
			planificarHorarios(); 
			chainJobs(archivos(),recalculo());
			empezarPlanificador();
		} catch (SchedulerException e) {
			cerrarPlanificador();
			e.printStackTrace();
		}
	}

	private void cerrarPlanificador() {
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void empezarPlanificador() throws SchedulerException {
		scheduler.start();
	}

	public void planificarHorarios() throws SchedulerException {
		planificar(CargaArchivosProgramada.class, horarioCargaEmpresas(), archivos());
	}
  
	private void planificar (Class<? extends Job> clase, String schedule, String identidad) throws SchedulerException {
		JobDetail job = JobBuilder.newJob(clase).withIdentity(identidad).build();
		Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(schedule)).build();
		addToSchedule(job, trigger);
	}

	public void addToSchedule(JobDetail job, Trigger trigger) throws SchedulerException {
		scheduler.scheduleJob(job, trigger);
	}

	public void chainJobs(String primerEvento, String segundoEvento) {
		JobChainingJobListener chain = new JobChainingJobListener("encadenador");
		JobKey primer = new JobKey(primerEvento, "Grupo1");
		JobKey segundo = new JobKey(segundoEvento, "Grupo1");
		chain.addJobChainLink(primer, segundo);
	}

	private String horarioCargaEmpresas() {
		return PropertyReader.readProperty("horarioCarga");
	}
  	
	private String archivos() {
		return "CargaArchivos";
	}
	
	private String recalculo() {
		return "RecalculoIndicadores";
	}
	
	public static Planificador instance() {
		if (noHayInstanciaCargada())
			cargarNuevaInstancia();
		return instance;
	}

	public static void cargarNuevaInstancia() {
		instance = new Planificador();
	}

	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}
}
