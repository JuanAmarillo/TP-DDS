package domain.repositorios;

import java.util.List;
import java.util.NoSuchElementException;

import domain.Empresa;

public class RepositorioEmpresas extends Repositorio<Empresa> {

	private static RepositorioEmpresas instance = null;

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

	@Override
	protected Class<Empresa> getEntity() {
		return Empresa.class;
	}

	@Override
	public void agregar(Empresa empresa) {
		try {
			agregarCuentas(empresa);
		} catch (NoSuchElementException e) {
			super.agregar(empresa);
		}
	}

	public void agregarCuentas(Empresa unaEmpresa) {
		Empresa empresa = findByName(unaEmpresa.getNombre()).get();
		empresa.agregarCuentas(unaEmpresa.getCuentas());
	}
	
	public boolean verificarExistencia(Empresa empresa) {
		return verificarExistencia(empresa.getNombre());
	}

	public List<String> getPeriodos() {
		return getEntityManager().createQuery("select unique periodo from Cuenta").getResultList();
	}
}