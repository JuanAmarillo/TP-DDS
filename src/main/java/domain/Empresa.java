package domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.JodaTimePermission;
import org.uqbar.commons.utils.Observable;


@Observable
public class Empresa {
	private String nombre;
	private int anioFundacion;
	private Set<Cuenta> cuentas = new HashSet<>();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public int getAnioFundacion() {
		return anioFundacion;
	}

	public void setAnioFundacion(int anioFundacion) {
		this.anioFundacion = anioFundacion;
	}

	public Set<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public void agregarCuentas(Set<Cuenta> cuentas){
		this.cuentas.addAll(cuentasSinRepetidos(cuentas));
	}
	
	public Set<Cuenta> cuentasSinRepetidos(Set<Cuenta> cuentas){
		return cuentas.stream().filter(cuenta ->  !contieneLaCuenta(cuenta)).collect(Collectors.toSet());
	}

	public boolean contieneLaCuenta(Cuenta cuenta) {
		return cuentas.stream().anyMatch(cuentita -> cuentita.esIgualA(cuenta));
	}
	
	public boolean contieneLaCuentaDePeriodo(String nombre,String periodo){
		return  cuentas.stream().anyMatch(cuenta -> cuenta.deNombre(nombre) && cuenta.dePeriodo(periodo));
	}

	public Set<Cuenta> getCuentasSegun(String periodo) {
		return this.getCuentas().stream().filter(unaCuenta -> unaCuenta.dePeriodo(periodo))
				.collect(Collectors.toSet());
	}

	public Set<String> getPeriodos() {
		return this.getCuentas().stream().map(unaCuenta -> unaCuenta.getPeriodo()).collect(Collectors.toSet());
	}
	
	public Boolean esLaMismaEmpresaQue(Empresa empresa) {
		return this.getNombre().equals(empresa.getNombre());
	}

	public Double getValorDeLaCuenta(String nombre,String periodo) {
		return buscarCuentaDe(nombre, periodo).getBalance();
	}

	private Cuenta buscarCuentaDe(String nombre, String periodo) {
		return cuentas.stream().filter(cuenta -> cuenta.deNombre(nombre) && cuenta.dePeriodo(periodo)).findFirst().
				get();
	}

	public int antiguedad() {
		DateTime dt = new DateTime();
		return dt.getYear() - anioFundacion;
	}
	
	
}
