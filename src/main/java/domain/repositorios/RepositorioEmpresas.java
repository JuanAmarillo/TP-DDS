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

public class RepositorioEmpresas {
	private static List<Empresa> empresasCargadas = datosDePrueba();
	
	public static List<Empresa> getEmpresasCargadas() {
		return empresasCargadas;
	}
	
	public static Empresa getEmpresa(Empresa empresa){
		return empresasCargadas.stream().filter(unaEmpresa -> unaEmpresa.equals(empresa)).findFirst().get();
	}
	
	public static List<Cuenta> getCuentasSegun(Empresa empresa,LocalDate periodo){
		return getEmpresa(empresa).getCuentas().stream().filter(unaCuenta -> unaCuenta.getPeriodo().equals(periodo))
				.collect(Collectors.toList());
	}
	public static Set<LocalDate> getPeriodosDe(Empresa empresa){
		return getEmpresa(empresa).getCuentas().stream().map(unaCuenta -> unaCuenta.getPeriodo())
				.collect(Collectors.toSet());
	}
	public static Set<LocalDate> getPeriodos(){
		Set<LocalDate> periodos = new HashSet<LocalDate>();
		empresasCargadas.stream().forEach(unaEmpresa ->periodos.addAll(getPeriodosDe(unaEmpresa)));
		return periodos;
	}
	
	public static List<Empresa> datosDePrueba(){
		List<Empresa> empresas = new ArrayList<Empresa>();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Empresa unaEmpresa = new Empresa();
		
		cuentas.add(agregarCuentaDePrueba("ROE", 102, new LocalDate(2017,12,04)));
		cuentas.add(agregarCuentaDePrueba("ROA", 90, new LocalDate(2017,12,04)));
		
		unaEmpresa.setNombre("Google");
		unaEmpresa.setCuentas(cuentas);
		empresas.add(unaEmpresa);
		
		return empresas;
		
		
	}
	public static Cuenta agregarCuentaDePrueba(String nombre, int balance, LocalDate periodo){
		Cuenta unaCuenta = new Cuenta();
		unaCuenta.setNombre(nombre);
		unaCuenta.setBalance(balance);
		unaCuenta.setPeriodo(periodo);
		return unaCuenta;
	}
	
}
