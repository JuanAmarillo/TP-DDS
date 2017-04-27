package domain.repositorios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import domain.Empresa;
import domain.Periodo;

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
}