package domain.repositorios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import domain.Empresa;

public class RepositorioEmpresas implements Repositorio<Empresa>{
	private static RepositorioEmpresas instance = null;
	private EntityManager entityManager = PerThreadEntityManagers.getEntityManager();

	public static RepositorioEmpresas instance() {
		if (noHayInstanciaCargada()) 
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		instance = new RepositorioEmpresas();
	}

	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}

	public static void resetSingleton() {
		instance = null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Empresa> obtenerLista(String query){
		return entityManager.createQuery(query).getResultList();
	}
	
	public List<Empresa> getEmpresasCargadas() {
		return obtenerLista("from Empresa");
	}

	public void agregarEmpresa(Empresa empresa) { 
		EntityTransaction tx = crearTransaccion();
		persistirEmpresa(empresa);
		tx.commit();
	}

	private EntityTransaction crearTransaccion() {
		EntityTransaction tx = entityManager.getTransaction();
		if(!tx.isActive()) tx.begin();
		return tx;
	}

	private void persistirEmpresa(Empresa empresa) {
		entityManager.persist(empresa);
	}
	
	
	public void agregarDesdeArchivo(Empresa empresaLeida) {
		try{
			Empresa empresa = buscarEmpresa(empresaLeida.getNombre()).get();			
			empresa.agregarCuentas(empresaLeida.getCuentas());
		}
		catch(NoSuchElementException e){
			agregarEmpresa(empresaLeida);
		}
	}

	public boolean existeLaEmpresa(Empresa empresa) { 
		return buscarEmpresa(empresa.getNombre()).isPresent();
	}

	public Optional<Empresa> buscarEmpresa(String nombre) {
		return obtenerLista("from Empresa where nombre= '" + nombre + "'")
									  .stream()
									  .findFirst();
	}

	public Boolean tieneEmpresasCargadas() {
		return cantidadDeEmpresasCargadas() != 0;// select from Empresas SI EXISTE
	}
	
	public Long cantidadDeEmpresasCargadas(){
		return (Long) entityManager.createQuery("select count(*) from Empresa").getSingleResult();
	}
	@SuppressWarnings("unchecked")
	public List<String> getNombreEmpresas() {
		return entityManager.createQuery("select nombre from Empresa").getResultList();// select nombre from empresas
	}

	public List<String> getPeriodos() {
		return entityManager.createQuery("select unique periodo from Cuenta").getResultList();// select periodos from cuentas
	}
	
	public void borrarEmpresa(String empresa){
		EntityTransaction tx = crearTransaccion();
		entityManager.createQuery("delete from Empresa where nombre = '" + empresa + "'").executeUpdate();
		tx.commit();
	}
	
	public void resetEM() {
		entityManager.flush();
	}
}