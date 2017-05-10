package domain.repositorios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hamcrest.core.IsNull;

import domain.Empresa;


public class RepositorioEmpresas {
	private static List<Empresa> empresasCargadas;
	private static RepositorioEmpresas instance = null;

	public static RepositorioEmpresas instance() {
		if (instance == null) {
			empresasCargadas = new ArrayList<Empresa>();
			instance = new RepositorioEmpresas();
		}
		return instance;
	}

	public static void resetSingleton() {
		instance = null;
	}

	public void agregarEmpresa(Empresa empresa){
		this.getEmpresasCargadas().add(empresa);
	}
	

	public List<Empresa> getEmpresasCargadas() {
		return empresasCargadas;
	}


	public void loadEmpresa(Empresa empresaLeida) {
		if(existeLaEmpresa(empresaLeida)) 
			agregarCuentas(empresaLeida);
		else 
			agregarEmpresa(empresaLeida); 
	}

	private void agregarCuentas(Empresa empresaLeida) {
		Empresa empresa = buscarEmpresa(empresaLeida.getNombre());
		empresa.agregarCuentas(empresaLeida.getCuentas());
	}

	private boolean existeLaEmpresa(Empresa empresa) {
		return empresasCargadas.stream().anyMatch(unaEmpresa->unaEmpresa.getNombre().equals(empresa.getNombre()));
	}

	public Empresa buscarEmpresa(String nombre) {
		return empresasCargadas.stream().filter(empresa -> empresa.getNombre().equals(nombre)).findFirst().orElse(null);
	}
	
}