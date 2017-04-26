package domain.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.uqbar.commons.utils.Observable;

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
	
	public static List<Empresa> datosDePrueba(){
		List<Empresa> empresas = new ArrayList<Empresa>();
		Empresa unaEmpresa = new Empresa();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta unaCuenta = new Cuenta();
		
		unaCuenta.setNombre("ROE");
		unaCuenta.setBalance(102);
		unaCuenta.setPeriodo(new LocalDate(2017,12,04));
		cuentas.add(unaCuenta);
		
		unaEmpresa.setNombre("Google");
		unaEmpresa.setCuentas(cuentas);
		empresas.add(unaEmpresa);
		
		return empresas;
		
		
	}
	
}
