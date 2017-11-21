package batchProccessing;

<<<<<<< HEAD
=======
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
>>>>>>> eff19c5baaee96c9e75739b12f2568f17af5a719
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

public class Planificador {

	public static Scheduler planificador;
	
	public static void begin() {
		try {
			planificarHorarios(); 
			chainJobs(archivos(),recalculo());
			empezarPlanificador();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}	

	private static void empezarPlanificador() throws SchedulerException {
		planificador = StdSchedulerFactory.getDefaultScheduler();
		planificador.start();
	}

	private static void planificarHorarios() throws SchedulerException {
		planificar(CargaArchivosProgramada.class, CincoAMTodosLosDias(), archivos());
	}
  
	private static void planificar (Class<? extends Job> clase, String schedule, String identidad) throws SchedulerException {
		JobDetail job = JobBuilder.newJob(clase).build();
		Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(schedule)).withIdentity(identidad).build();
		planificador.scheduleJob(job, trigger);
	}

	private static void chainJobs(String primerEvento, String segundoEvento) {
		JobChainingJobListener chain = new JobChainingJobListener("encadenador");
		JobKey primer = new JobKey(primerEvento, "Grupo1");
		JobKey segundo = new JobKey(segundoEvento, "Grupo1");
		chain.addJobChainLink(primer, segundo);
	}
  
	private static String CincoAMTodosLosDomingos() {
		return "0 0 5 * * 1";
	}

	private static String CincoAMTodosLosDias() {
		return "0 0 5 * * ?";
	}
  	
	private static String archivos() {
		return "CargaArchivos";
	}
	
	private static String recalculo() {
		return "RecalculoIndicadores";
	}
}
