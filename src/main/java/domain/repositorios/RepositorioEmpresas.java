package domain.repositorios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;
import org.junit.experimental.theories.Theories;

import domain.Cuenta;
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

	public void agregarEmpresa(Empresa empresa) {
		this.getInstance().getEmpresasCargadas().add(empresa);
	}

	public List<Empresa> getEmpresasCargadas() {
		return getInstance().empresasCargadas;
	}

	public Set<Periodo> getPeriodos() {
		Set<Periodo> periodos = new HashSet<Periodo>();
		empresasCargadas.stream().forEach(unaEmpresa -> periodos.addAll(unaEmpresa.getPeriodos()));
		return periodos;
	}
}