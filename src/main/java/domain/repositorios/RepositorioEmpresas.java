package domain.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.joda.time.LocalTime;

import domain.Cuenta;
import domain.Empresa;

public class RepositorioEmpresas {
	public static List<Empresa> empresasCargadas = new ArrayList<Empresa>();
	
	public static List<Empresa> getEmpresas(){
		return empresasCargadas;
	}
	
	public static Empresa getEmpresa(Empresa empresa){
		return empresasCargadas.stream().filter(unaEmpresa -> unaEmpresa.getNombre().equals(empresa)).findFirst().get();
	}
	public static List<Cuenta> getCuentasSegun(Empresa empresa,LocalTime periodo){
		return getEmpresa(empresa).getCuentas().stream().filter(unaCuenta -> unaCuenta.getPeriodo().equals(periodo))
				.collect(Collectors.toList());
	}
}
