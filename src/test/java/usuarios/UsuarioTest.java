package usuarios;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import domain.condiciones.condicionesPredeterminadas.CEndeudamiento;
import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;
import domain.login.Usuario;
import domain.metodologias.Metodologia;
import domain.repositorios.RepositorioMetodologias;
import domain.repositorios.RepositorioUsuarios;

public class UsuarioTest extends AbstractPersistenceTest{
	
	Usuario gabriel = new Usuario("Gabriel", "ElMasCapito", "soyyo");
	Usuario fernando = new Usuario("Fernando", "ElMasCapo", "ahorasoyyo");
	
	Metodologia metodologiaMixta = new Metodologia("PepitaMixta", Arrays.asList(new TEmpresaMas10Años(), new CEndeudamiento(5.0)));
	Metodologia metodologiaTaxativa = new Metodologia("PepitaTaxativa", Arrays.asList(new TEmpresaMas10Años()));
	Metodologia metodologiaComparativa = new Metodologia("PepitaComparativa", Arrays.asList(new CEndeudamiento(5.0)));	
	
	@Before
	public void init() {
		RepositorioUsuarios.instance().agregar(gabriel);
		RepositorioUsuarios.instance().agregar(fernando);
		gabriel.agregarMetodologia(metodologiaComparativa);
		gabriel.agregarMetodologia(metodologiaMixta);
		fernando.agregarMetodologia(metodologiaTaxativa);
	}
	
	@Test
	public void traeLasDeFernando() {
		Usuario usuario = RepositorioUsuarios.instance().find("nombreCuenta", "ElMasCapito").get();
		assertEquals(2, usuario.getMetodologias().size());
	}
	@Override
	public EntityManager entityManager() {
		return RepositorioUsuarios.instance().getEntityManager();
	}

}
