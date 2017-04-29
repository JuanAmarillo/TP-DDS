package domain.repositorios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import domain.Empresa;
import exceptions.NoExisteLaEmpresaException;


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

	public Set<String> getPeriodos() {
		Set<String> periodos = new HashSet<String>();
		empresasCargadas.stream().forEach(unaEmpresa -> periodos.addAll(unaEmpresa.getPeriodos()));
		return periodos;
	}
	
	public Empresa getEmpresaCargada(Empresa aBuscar){
		try {
			Empresa aDevolver = this.getEmpresasCargadas().stream().filter(emp -> emp.esLaMismaEmpresaQue(aBuscar)).collect(Collectors.toList()).get(0);
			return aDevolver;
		}
		catch (IndexOutOfBoundsException e) {
			throw new NoExisteLaEmpresaException();
		}
	}

	public boolean yaEstaCargada(Empresa empresaLeida) {
		// TODO Auto-generated method stub
		return false;
	}
}