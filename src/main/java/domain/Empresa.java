package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;


@Observable
public class Empresa {
	private String nombre;
	private Set<Cuenta> cuentas = new HashSet<>();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public List<Cuenta> getCuentasSegun(String string) {
		return this.getCuentas().stream().filter(unaCuenta -> unaCuenta.getPeriodo().equals(string))
				.collect(Collectors.toList());
	}

	public Set<String> getPeriodos() {
		return this.getCuentas().stream().map(unaCuenta -> unaCuenta.getPeriodo()).collect(Collectors.toSet());
	}
	
	public Boolean esLaMismaEmpresaQue(Empresa empresa) {
		return this.getNombre().equals(empresa.getNombre());
	}
	
}
