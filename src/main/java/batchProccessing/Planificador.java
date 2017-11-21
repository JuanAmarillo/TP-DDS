package batchProccessing;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.listeners.JobChainingJobListener;

public class Planificador {

	public static Scheduler planificador;
	
	public static void begin() {
		try {
			planificador = StdSchedulerFactory.getDefaultScheduler();
			planificar(CargaArchivosProgramada.class, "0 0 5 * * ?", "CargaArchivos");  // A las 5 AM todos los dias
			planificar(CalculoDeIndicadoresProgramado.class, "0 0 5 * * 1", "CalculoIndicadores"); // A las 5 AM de los domingos
			chainJobs("CargaArchivos",  "CalculoIndicadores");
			planificador.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	private static void chainJobs(String primerEvento, String segundoEvento) {
		JobChainingJobListener chain = new JobChainingJobListener("encadenador");
		JobKey primer = new JobKey(primerEvento, "Grupo1");
		JobKey segundo = new JobKey(segundoEvento, "Grupo1");
		chain.addJobChainLink(primer, segundo);
	}

	private static void planificar (Class<? extends Job> clase, String schedule, String identidad) throws SchedulerException {
		JobDetail job = JobBuilder.newJob(clase).build();
		Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(schedule)).withIdentity(identidad).build();
		planificador.scheduleJob(job, trigger);
	}
}
