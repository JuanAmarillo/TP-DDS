package unitTests.batch;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.quartz.JobKey;
import org.quartz.SchedulerException;

import batchProccessing.Planificador;
import domain.repositorios.RepositorioIndicadores;
import mocks.ProcesoAsincronicoMock;

public class PlanificadorTest {
	
	Planificador planificador;
	
	@Before
	public void antes() {
		planificador = new Planificador();
	}
	
	@After
	public void despues() throws SchedulerException {
		planificador.scheduler.shutdown();
	}
	
	@Test
	public void seAgregaAlPlanificador() throws SchedulerException {
		planificador.planificarHorarios();
		assertTrue(planificador.scheduler.checkExists(nuevoJobKey("CargaArchivos")));
	}
	
	@Test
	public void seEjecutaEn3Segundos() throws SchedulerException, InterruptedException {
		ProcesoAsincronicoMock proc = nuevoMock();
		agregarAlPlanificador(proc, "Uno", true);
		planificador.empezarPlanificador();
		Thread.sleep(2 * 1000);
		assertTrue(proc.booleanoDePrueba);		
	}
	
	/*@Test
	public void encadenarTrabajos() throws SchedulerException, InterruptedException {
		ProcesoAsincronicoMock proc1 = nuevoMock();
		ProcesoAsincronicoMock proc2 = nuevoMock();
		agregarAlPlanificador(proc1, "Uno", true);
		agregarAlPlanificador(proc2, "Dos", false);
		planificador.chainJobs("Uno", "Dos");
		planificador.empezarPlanificador();
		Thread.sleep(3*1000);
		assertTrue(ProcesoAsincronicoMock.booleanoDePrueba);
		assertTrue(ProcesoAsincronicoMock.paraChains);
	}
*/
	private void agregarAlPlanificador(ProcesoAsincronicoMock proc1, String identidad, Boolean conCrono) throws SchedulerException {
		planificador.addToSchedule(proc1.job(identidad), proc1.trigger(conCrono));
	}

	private ProcesoAsincronicoMock nuevoMock() {
		return new ProcesoAsincronicoMock();
	}
	
	private JobKey nuevoJobKey(String key) {
		return new JobKey(key);
	}
}
