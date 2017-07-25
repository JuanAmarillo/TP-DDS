package domain;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.JodaTimePermission;
import org.uqbar.commons.utils.Observable;

@Observable
public class Empresa {
	private String nombre;
	private Integer anioFundacion;
	private Set<Cuenta> cuentas = new HashSet<>();
	private Double peso = 0.0;

	public String getNombre() {
		return nombre;
	}

	public Empresa setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public Integer getAnioFundacion() {
		return anioFundacion;
	}

	public void setAnioFundacion(Integer anioFundacion) {
		this.anioFundacion = anioFundacion;
	}

	public Set<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	//
	public void agregarCuentas(Set<Cuenta> cuentas) {
		this.cuentas.addAll(cuentasSinRepetidos(cuentas));
	}
//
	public Set<Cuenta> cuentasSinRepetidos(Set<Cuenta> cuentas) {
		return cuentas.stream().filter(cuenta -> !contieneLaCuenta(cuenta)).collect(Collectors.toSet());
	}

	public boolean contieneLaCuenta(Cuenta cuenta) {
		return cuentas.stream().anyMatch(unaCuenta -> unaCuenta.esIgualA(cuenta));
	}

	public boolean contieneLaCuentaDePeriodo(String nombre, String periodo) {
		return cuentas.stream().anyMatch(cuenta -> cuenta.deNombre(nombre) && cuenta.dePeriodo(periodo));
	}

	public Set<Cuenta> getCuentasSegun(String periodo) {
		return this.getCuentas().stream().filter(unaCuenta -> unaCuenta.dePeriodo(periodo)).collect(Collectors.toSet());
	}

	public Set<String> getPeriodos() {
		return this.getCuentas().stream().map(unaCuenta -> unaCuenta.getPeriodo()).collect(Collectors.toSet());
	}

	public Boolean esLaMismaEmpresaQue(Empresa empresa) {
		return this.getNombre().equals(empresa.getNombre());
	}

	public Double getValorDeLaCuenta(String nombre, String periodo) {
		return buscarCuentaDe(nombre, periodo).get().getBalance();
	}

	public Optional<Cuenta> buscarCuentaDe(String nombre, String periodo) {
		return cuentas.stream().filter(cuenta -> cuenta.deNombre(nombre) && cuenta.dePeriodo(periodo)).findFirst();
	}

	public void sumarPeso(double d) {
		peso += d;
	}
	
	public Double getPeso() {
		return peso;
	}

	public void resetPeso() {
		peso = 0.0;
	}

}
