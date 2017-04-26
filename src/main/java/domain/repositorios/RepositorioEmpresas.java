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
	
	public static List<Empresa> getEmpresasCargadas() {
		return empresasCargadas;
	}
		
	public static List<Cuenta> getCuentasSegun(Empresa empresa,Periodo periodo){
		return empresa.getCuentas().stream().filter(unaCuenta -> unaCuenta.getPeriodo().equals(periodo))
				.collect(Collectors.toList());
	}
	
	public static Set<Periodo> getPeriodosDe(Empresa empresa){
		return empresa.getCuentas().stream().map(unaCuenta -> unaCuenta.getPeriodo())
				.collect(Collectors.toSet());
	}
	
	public static Set<Periodo> getPeriodos(){
		Set<Periodo> periodos = new HashSet<Periodo>();
		empresasCargadas.stream().forEach(unaEmpresa ->periodos.addAll(getPeriodosDe(unaEmpresa)));
		return periodos;
	}
}