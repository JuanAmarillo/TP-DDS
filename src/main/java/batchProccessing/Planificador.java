package batchProccessing;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Planificador {

	public static Scheduler planificador;
	
	public static void begin() {
		try {
			planificador = StdSchedulerFactory.getDefaultScheduler();
			planificar(CargaArchivosProgramada.class, "0 0 5 * * ?");  // A las 5 AM todos los dias
			planificar(CalculoDeIndicadoresProgramado.class, "0 0 5 * * 1"); // A las 5 AM de los domingos
			planificador.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	private static void planificar (Class<? extends Job> clase, String schedule) throws SchedulerException {
		JobDetail job = JobBuilder.newJob(clase).build();
		Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(schedule)).build();
		planificador.scheduleJob(job, trigger);
	}
}
