package domain.repositorios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import domain.Empresa;

public class RepositorioEmpresas implements Repositorio<Empresa>{
	private static RepositorioEmpresas instance = null;
	private List<Empresa> empresasCargadas = new ArrayList<>();

	public static RepositorioEmpresas instance() {
		if (noHayInstanciaCargada()) 
			cargarNuevaInstancia();
		return instance;
	}

	private static void cargarNuevaInstancia() {
		instance = new RepositorioEmpresas();
		instance.empresasCargadas = new ArrayList<Empresa>();
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
	
	public void agregarDesdeArchivo(Empresa empresaLeida) { //testear 
		if (existeLaEmpresa(empresaLeida))
			agregarCuentas(empresaLeida);
		else
			agregarEmpresa(empresaLeida);
	}

	private void agregarCuentas(Empresa empresaLeida) {
		buscarEmpresa(empresaLeida.getNombre()).get().agregarCuentas(empresaLeida.getCuentas());
	}

	public boolean existeLaEmpresa(Empresa empresa) { 
		return empresasCargadas.stream().anyMatch(segunNombre(empresa.getNombre()));
	}

	public Optional<Empresa> buscarEmpresa(String nombre) {
		return empresasCargadas.stream().filter(segunNombre(nombre)).findFirst();
	}

	private Predicate<? super Empresa> segunNombre(String nombre) {
		return empresa -> empresa.suNombreEs(nombre);
	}

	public Boolean tieneEmpresasCargadas() {
		return cantidadDeEmpresasCargadas() > 0;
	}
	
	public Integer cantidadDeEmpresasCargadas(){
		return empresasCargadas.size();
	}

	public List<String> getNombreEmpresas() {
		return getEmpresasCargadas().stream().map(empresa -> empresa.getNombre()).collect(Collectors.toList());
	}

	public List<String> getPeriodos() {
		Set<String> periodos = new HashSet<String>();
		getEmpresasCargadas().stream().forEach(empresa -> periodos.addAll(empresa.getPeriodos()));
		return periodos.stream().collect(Collectors.toList());
	}

}