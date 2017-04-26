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
	private static List<Empresa> empresasCargadas = datosDePrueba();
	
	public static List<Empresa> getEmpresasCargadas() {
		return empresasCargadas;
	}
	
	/*public static Empresa getEmpresa(Empresa empresa){
		return empresasCargadas.stream().filter(unaEmpresa -> unaEmpresa.equals(empresa)).findFirst().get();
	}*/
	
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
	
	public static List<Empresa> datosDePrueba(){
		List<Empresa> empresas = new ArrayList<Empresa>();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Empresa unaEmpresa = new Empresa();
		Periodo periodo1 = new Periodo(new LocalDate(2017,07,01), new LocalDate(2017,12,31));
		Periodo periodo2 = new Periodo(new LocalDate(2017,01,01), new LocalDate(2017,06,30));
		cuentas.add(agregarCuentaDePrueba("ROE", 102, periodo1));
		cuentas.add(agregarCuentaDePrueba("ROA", 90, periodo2));
		
		unaEmpresa.setNombre("Google");
		unaEmpresa.setCuentas(cuentas);
		empresas.add(unaEmpresa);
		
		return empresas;
	}
	
	public static Cuenta agregarCuentaDePrueba(String nombre, int balance, Periodo periodo){
		Cuenta unaCuenta = new Cuenta();
		unaCuenta.setNombre(nombre);
		unaCuenta.setBalance(balance);
		unaCuenta.setPeriodo(periodo);
		return unaCuenta;
	}
	
}
