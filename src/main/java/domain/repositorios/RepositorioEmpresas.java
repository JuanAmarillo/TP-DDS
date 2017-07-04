package domain.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.Empresa;

public class RepositorioEmpresas implements Repositorio<Empresa>{
	private static List<Empresa> empresasCargadas;
	private static RepositorioEmpresas instance = null;

	public static RepositorioEmpresas instance() {
		if (noHayInstanciaCargada()) 
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		empresasCargadas = new ArrayList<Empresa>();
		instance = new RepositorioEmpresas();
	}

	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}

	public static void resetSingleton() {
		instance = null;
	}

	public void agregarEmpresa(Empresa empresa) {
		this.getEmpresasCargadas().add(empresa);
	}

	public List<Empresa> getEmpresasCargadas() {
		return empresasCargadas;
	}
	
	public void agregarDesdeArchivo(Empresa empresaLeida) {
		if (existeLaEmpresa(empresaLeida))
			agregarCuentas(empresaLeida);
		else
			agregarEmpresa(empresaLeida);
	}

	private void agregarCuentas(Empresa empresaLeida) {
		Empresa empresa = buscarEmpresa(empresaLeida.getNombre());
		empresa.agregarCuentas(empresaLeida.getCuentas());
	}

	private boolean existeLaEmpresa(Empresa empresa) {
		return empresasCargadas.stream().anyMatch(unaEmpresa -> unaEmpresa.getNombre().equals(empresa.getNombre()));
	}

	public Empresa buscarEmpresa(String nombre) {
		return empresasCargadas.stream().filter(empresa -> empresa.getNombre().equals(nombre)).findFirst().orElse(null);
	}

	public boolean tieneEmpresasCargadas() {
		return empresasCargadas.size() > 0;
	}

}