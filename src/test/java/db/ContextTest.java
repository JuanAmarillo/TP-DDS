package db;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import domain.Cuenta;
import domain.Empresa;
import domain.condiciones.Condicion;
import domain.condiciones.condicionesPredeterminadas.CEmpresaMayorAntiguedad;
import domain.condiciones.condicionesPredeterminadas.CEndeudamiento;
import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;
import domain.metodologias.Metodologia;
import domain.repositorios.RepositorioEmpresas;
import domain.repositorios.RepositorioMetodologias;

public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private static Empresa prepararEmpresa(String nombre, Double pasivo, Double activo, int anioFundacion) {
		Empresa empresa = new Empresa(nombre);
		Set<Cuenta> cuentas = new HashSet<>();
		Cuenta cuentita = new Cuenta("PasivoTotal", "pascuas", pasivo);
		Cuenta cuentitaBis = new Cuenta("ActivoTotal", "pascuas", activo);
		cuentas.add(cuentita);
		cuentas.add(cuentitaBis);
		empresa.setCuentas(cuentas);
		empresa.setAnioFundacion(anioFundacion);
		return empresa;
	}
	
	Condicion condicionTAntiguedad = new TEmpresaMas10Años();
	Condicion condicionCEndeudamiento = new CEndeudamiento(5.0);
	Condicion condicionCAntiguedad = new CEmpresaMayorAntiguedad(10.0);
		
	List<Condicion> condicionesMixtas = Arrays.asList(condicionTAntiguedad, condicionCEndeudamiento);
	
	Metodologia metodologiaMixta = new Metodologia("PepitaMixta", condicionesMixtas);
	
	@Test
	public void contextUp() {
		assertNotNull(entityManager());
	}

	@Test
	public void contextUpWithTransaction() throws Exception {
		withTransaction(() -> {});
	}

	@Test
	public void agregandoEmpresa(){
		Empresa empresa = this.prepararEmpresa("Coca", 2000.0, 1000.0, 2017);
		RepositorioEmpresas.instance().agregar(empresa);
	}
	
	@Test
	public void agregandoMetodologia(){
		RepositorioMetodologias.instance().agregar(metodologiaMixta);
	}
}
