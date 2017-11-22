package unitTests.batch;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.quartz.JobKey;
import org.quartz.SchedulerException;

import batchProccessing.Planificador;
import domain.repositorios.RepositorioIndicadores;
import mocks.ProcesoAsincronicoMock;

public class AsincronicosTest {
	
	Planificador planificador;
	
	@Before
	public void antes() {
		planificador = new Planificador();
	}
	
	@Test
	public void seAgregaAlPlanificador() throws SchedulerException {
		planificador.planificarHorarios();
		assertTrue(planificador.scheduler.checkExists(nuevoJobKey("CargaArchivos")));
	}
	
	@Test
	public void seEjecutaEn3Segundos() throws SchedulerException, InterruptedException {
		ProcesoAsincronicoMock proc = new ProcesoAsincronicoMock();
		planificador.addToSchedule(proc.job(), proc.trigger());
		planificador.empezarPlanificador();
		Thread.sleep(4 * 1000);
		assertTrue(proc.booleanoDePrueba);		
	}
	
	private JobKey nuevoJobKey(String key) {
		return new JobKey(key);
	}
}
