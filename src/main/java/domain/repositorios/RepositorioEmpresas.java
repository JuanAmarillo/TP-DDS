package domain.repositorios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import domain.Empresa;
import domain.interfaces.Periodo;


public class RepositorioEmpresas {
	private static List<Empresa> empresasCargadas;
	private static RepositorioEmpresas instance = null;

	public static RepositorioEmpresas getInstance() {
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

	public Set<Periodo> getPeriodos() {
		Set<Periodo> periodos = new HashSet<Periodo>();
		empresasCargadas.stream().forEach(unaEmpresa -> periodos.addAll(unaEmpresa.getPeriodos()));
		return periodos;
	}
	
	public Empresa obtenerEmpresaYaCargada(Empresa aBuscar){
		try {
			Empresa aDevolver = this.getEmpresasCargadas().stream().filter(emp -> emp.getNombre() == aBuscar.getNombre()).collect(Collectors.toList()).get(0);
			return aDevolver;
		}
		catch (IndexOutOfBoundsException e) {
			return new Empresa();
		}
	}
}