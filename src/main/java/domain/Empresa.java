package domain;

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

	public void agregarCuentas(Set<Cuenta> cuentas){
		this.cuentas.addAll(cuentasSinRepetidos(cuentas));
	}
	
	public Set<Cuenta> cuentasSinRepetidos(Set<Cuenta> cuentas){
		return cuentas.stream().filter(cuenta ->  !contieneLaCuenta(cuenta)).collect(Collectors.toSet());
	}

	public boolean contieneLaCuenta(Cuenta cuenta) {
		return cuentas.stream().anyMatch(cuentita -> cuentita.esIgualA(cuenta));
	}
	
	public boolean contieneLaCuenta(String nombre){
		return  cuentas.stream().anyMatch(cuenta -> cuenta.suNombreEs(nombre));
	}

	public Set<Cuenta> getCuentasSegun(String periodo) {
		return this.getCuentas().stream().filter(unaCuenta -> unaCuenta.getPeriodo().equals(periodo))
				.collect(Collectors.toSet());
	}

	public Set<String> getPeriodos() {
		return this.getCuentas().stream().map(unaCuenta -> unaCuenta.getPeriodo()).collect(Collectors.toSet());
	}
	
	public Boolean esLaMismaEmpresaQue(Empresa empresa) {
		return this.getNombre().equals(empresa.getNombre());
	}

	public Double getValorDeLaCuenta(String cuenta) {
		return cuentas.stream().filter(c -> c.getNombre().equals(cuenta)).findFirst().get().getBalance();
	}

	public Double getValorDelIndicador(String indicador) {
		//Falta implementar
		return 0.0;
	}
	
	
}
