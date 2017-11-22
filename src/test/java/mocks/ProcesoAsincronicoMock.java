package mocks;

import java.time.LocalDateTime;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public class ProcesoAsincronicoMock implements Job {

	public static Boolean booleanoDePrueba = false;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		booleanoDePrueba = true;
		System.out.println("ME EJECUTE YAY");
	}
	
	public JobDetail job() {
		return JobBuilder.newJob().ofType(ProcesoAsincronicoMock.class).withIdentity("Mock").build();
	}
	
	public Trigger trigger() {
		return TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(enTresSegundos())).build();
	}

	private String enTresSegundos() {
		LocalDateTime h = LocalDateTime.now();
		return (h.getSecond()+3) + " * * * * ?";
	}
}
