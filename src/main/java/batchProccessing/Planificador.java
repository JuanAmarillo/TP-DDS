package batchProccessing;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Planificador {

	public static Scheduler planificador;
	
	public static void begin() {
		try {
			planificarHorarios(); 
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
		planificar(CargaArchivosProgramada.class, CincoAMTodosLosDias());
		planificar(CalculoDeIndicadoresProgramado.class, CincoAMTodosLosDomingos());
	}

	private static String CincoAMTodosLosDomingos() {
		return "0 0 5 * * 1";
	}

	private static String CincoAMTodosLosDias() {
		return "0 0 5 * * ?";
	}

	private static void planificar (Class<? extends Job> clase, String schedule) throws SchedulerException {
		JobDetail job = JobBuilder.newJob(clase).build();
		Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(schedule)).build();
		planificador.scheduleJob(job, trigger);
	}
}
