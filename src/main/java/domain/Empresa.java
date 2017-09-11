package domain;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

@Observable
public class Empresa {
	private String nombre;
	private Integer anioFundacion;
	private Set<Cuenta> cuentas = new HashSet<>();

	public String getNombre() {
		return nombre;
	}

	public Empresa(){}
	public Empresa(String nombre){
		this.nombre = nombre;
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
	
	public Boolean esLaMismaQue(Empresa empresa){
		return suNombreEs(getNombre());
	}
	
	public Boolean suNombreEs(String nombre){
		return this.getNombre().equals(nombre);
	}
	
	public void agregarCuentas(Set<Cuenta> cuentas) {
		this.cuentas.addAll(cuentasSinRepetidos(cuentas));
	}

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
}
