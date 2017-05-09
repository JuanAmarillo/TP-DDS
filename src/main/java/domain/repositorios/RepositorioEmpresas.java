package domain.repositorios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

	public Set<String> getPeriodos() {
		Set<String> periodos = new HashSet<String>();
		empresasCargadas.stream().forEach(unaEmpresa -> periodos.addAll(unaEmpresa.getPeriodos()));
		return periodos;
	}

	public void loadEmpresa(Empresa empresaLeida) {
		if(existeLaEmpresa(empresaLeida.getNombre())) {
			agregarCuentas(empresaLeida);
		}
		else {
			agregarEmpresa(empresaLeida); 
		}
	}

	private void agregarCuentas(Empresa empresaLeida) {
		Empresa empresa = buscarEmpresa(empresaLeida.getNombre()).get(0);
		empresa.agregarCuentas(empresaLeida.getCuentas());
	}

	private boolean existeLaEmpresa(String nombre) {
		int booleano = buscarEmpresa(nombre).size();
		return (booleano > 0) ? true : false;
	}

	public List<Empresa> buscarEmpresa(String nombre) {
		return empresasCargadas.stream().filter(empresa -> empresa.getNombre().equals(nombre)).collect(Collectors.toList());
	}
	
}