package domain;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.uqbar.commons.utils.Observable;

@Observable
@Entity
@Table(name = "empresas")
public class Empresa {


	@Id
	@GeneratedValue
	private Integer id;
	@Column(length=30)
	private String nombre;
	@Column
	private Integer anioFundacion;
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "empresa_id",  nullable=false)
	private Set<Cuenta> cuentas = new HashSet<>();

	

	public Empresa(){}
	public Empresa(String nombre){
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
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
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	public String getNombre() {
		return nombre;
	}

	public Optional<Cuenta> buscarCuentaDe(String nombre, String periodo) {
		return cuentas.stream().filter(cuenta -> cuenta.deNombre(nombre) && cuenta.dePeriodo(periodo)).findFirst();
	}
}
