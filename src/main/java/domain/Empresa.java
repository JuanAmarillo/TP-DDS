package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

@Observable
public class Empresa {
	private String nombre;
	private List<Cuenta> cuentas = new ArrayList<>();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public List<Cuenta> getCuentasSegun(Periodo periodo) {
		return this.getCuentas().stream().filter(unaCuenta -> unaCuenta.getPeriodo().equals(periodo))
				.collect(Collectors.toList());
	}

	public Set<Periodo> getPeriodos() {
		return this.getCuentas().stream().map(unaCuenta -> unaCuenta.getPeriodo()).collect(Collectors.toSet());
	}
}
